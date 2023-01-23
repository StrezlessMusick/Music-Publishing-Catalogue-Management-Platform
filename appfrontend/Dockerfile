## Use Node.js as the base image
#FROM node:lts-alpine
#
## Set the working directory
#WORKDIR /app
#
## Copy the frontend files
#COPY dist/* /app/
#COPY package.json package-lock.json /app/
#
## Install the dependencies for the frontend
##RUN npm install -g @angular/cli
#RUN npm install
#
## Expose the port
#EXPOSE 4200
#
## Start the server
#CMD ["ng", "build"]



FROM node:14-alpine

# Create app directory
WORKDIR /app

# Install app dependencies
COPY package.json package-lock.json ./
RUN npm install

# Bundle app source
COPY . .

# Build the app
RUN npm run build

# Serve the app using nginx
RUN apk add --no-cache nginx
#COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]

