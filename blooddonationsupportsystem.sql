CREATE TABLE role_user (
    role_id INT IDENTITY(1,1) PRIMARY KEY,
    role_name VARCHAR(20) UNIQUE NOT NULL
);


CREATE TABLE user_table (
  user_id UNIQUEIDENTIFIER PRIMARY KEY,
  password_hash VARCHAR(255), 
  full_name VARCHAR(100),
  phone_number VARCHAR(10) UNIQUE NOT NULL,
  address VARCHAR(255),
  longitude FLOAT,
  latitude FLOAT,
  avatar VARCHAR(255),
  date_of_birth DATE,
  gender VARCHAR(10) NULL,
  blood_type VARCHAR(3) DEFAULT NULL CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')), 
  created_at DATETIME DEFAULT GETDATE(),
  updated_at DATETIME DEFAULT GETDATE(),
  status VARCHAR(10) DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'BANNED', 'DISABLE')),
  role_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES role_user(role_id)
);

CREATE TABLE oauthaccount (
  oauthaccount_id UNIQUEIDENTIFIER PRIMARY KEY,
  provider VARCHAR(50) NOT NULL,       
  provider_user_id VARCHAR(255) NOT NULL, 
  created_at DATETIME DEFAULT GETDATE(),
  user_id UNIQUEIDENTIFIER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_table(user_id) ON DELETE CASCADE
)

CREATE TABLE passwordresetotp (
    passwordresetotp_id UNIQUEIDENTIFIER PRIMARY KEY,
    otp_code VARCHAR(10) NOT NULL,
    expires_at DATETIME NOT NULL,
    is_used BIT DEFAULT 0,
    created_at DATETIME DEFAULT GETDATE(),
	user_id UNIQUEIDENTIFIER NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user_table(user_id)
);

CREATE TABLE article_type (
	article_type_id UNIQUEIDENTIFIER PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description TEXT
);

CREATE TABLE article (
	article_id UNIQUEIDENTIFIER PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	content TEXT,
	created_at DATETIME DEFAULT GETDATE(),
	status VARCHAR(10) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'PUBLISHED', 'REJECTED')),
	type_id  UNIQUEIDENTIFIER NOT NULL,
	FOREIGN KEY (type_id) REFERENCES article_type(article_type_id)
);

CREATE TABLE image (
	image_id UNIQUEIDENTIFIER PRIMARY KEY,
	image_url TEXT,
	description TEXT NULL,
	created_at DATETIME DEFAULT GETDATE(),
	article_id UNIQUEIDENTIFIER NOT NULL,
	FOREIGN KEY (article_id) REFERENCES article(article_id)
);

CREATE TABLE register_emergency_blood_request (
  emergency_request_id UNIQUEIDENTIFIER PRIMARY KEY,
  request_date_at DATETIME DEFAULT GETDATE(), 
  description TEXT, 
  blood_type VARCHAR(3) DEFAULT NULL CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')), 
  set_time DATETIME,
  user_id UNIQUEIDENTIFIER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_table(user_id)
);

CREATE TABLE donation_registration (
    donation_registration_id UNIQUEIDENTIFIER PRIMARY KEY,
    registration_date TIMESTAMP NOT NULL,        
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED')),                  
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE(),
	donor_id UNIQUEIDENTIFIER NOT NULL,
	FOREIGN KEY (donor_id) REFERENCES user_table(user_id)
);

CREATE TABLE member_screening (
    member_screening_id UNIQUEIDENTIFIER PRIMARY KEY,
    screening_date DATETIME NOT NULL,
    health_status VARCHAR(20) DEFAULT 'PENDING' CHECK (health_status IN ('PASSED', 'FAILED', 'PENDING')) ,                   
    updated_blood_type VARCHAR(5) NOT NULL,             
    created_at DATETIME DEFAULT GETDATE(),
	donation_registration_id UNIQUEIDENTIFIER NOT NULL,
	screened_by_staff_id UNIQUEIDENTIFIER NOT NULL,
	FOREIGN KEY (donation_registration_id) REFERENCES donation_registration(donation_registration_id),
	FOREIGN KEY (screened_by_staff_id) REFERENCES user_table(user_id)
);

CREATE TABLE blood_donation_process(
  donation_process_id UNIQUEIDENTIFIER PRIMARY KEY,
  status NVARCHAR(255) DEFAULT 'SCREENING' CHECK (status IN ('SCREENING', 'COLLECTED')),
  volume_blood_collected INT
);

CREATE TABLE blood_donation_history(
  blood_donation_history_id UNIQUEIDENTIFIER PRIMARY KEY,
  donation_date DATETIME DEFAULT GETDATE(),
  volume_ml INT,
  donation_process_id UNIQUEIDENTIFIER,
  FOREIGN KEY (donation_process_id) REFERENCES blood_donation_process(donation_process_id)
);


CREATE TABLE blood_component_filtration(
  blood_component_filtration_id UNIQUEIDENTIFIER PRIMARY KEY, 
  blood_type VARCHAR(3) NOT NULL CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')),
  whole_blood INT,
  plasma INT,
  platelets INT,
  red_blood_cells INT,
  blood_donation_history_id UNIQUEIDENTIFIER,
  FOREIGN KEY (blood_donation_history_id) REFERENCES blood_donation_history(blood_donation_history_id)
);

CREATE TABLE blood_inventory(
  blood_inventory_id UNIQUEIDENTIFIER PRIMARY KEY, 
  blood_type VARCHAR(3) NOT NULL CHECK (blood_type IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')),
  whole_blood INT,
  plasma INT,
  platelets INT,
  red_blood_cells INT,
  total_unit INT,
  blood_component_filtration_id UNIQUEIDENTIFIER,
  FOREIGN KEY (blood_component_filtration_id) REFERENCES blood_component_filtration(blood_component_filtration_id)
);

CREATE TABLE external_hospital (
  hospital_id UNIQUEIDENTIFIER PRIMARY KEY,
  name NVARCHAR(50),
  phone_number  NVARCHAR(10), 
  address NVARCHAR(255)
);

CREATE TABLE visit (
    ip_address VARCHAR(50) PRIMARY KEY,
    path VARCHAR(255),         
    visited_at DATETIME DEFAULT GETDATE()
);

CREATE TABLE system_settings (
    setting_key VARCHAR(100) PRIMARY KEY,
    setting_value VARCHAR(MAX) NOT NULL,
    description VARCHAR(MAX),
    updated_at DATETIME DEFAULT GETDATE()
);
