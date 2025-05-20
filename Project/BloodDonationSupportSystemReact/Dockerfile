FROM node:18 AS build
WORKDIR /app/frontend

COPY . .
RUN npm install 18
RUN npm run build

FROM nginx:stable-alpine
COPY --from=build /app/frontend/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf