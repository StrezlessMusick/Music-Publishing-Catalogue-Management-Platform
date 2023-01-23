FROM node:14-alpine

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

# Serve the app using http-server
RUN npm install -g http-server
EXPOSE 4200
CMD ["http-server", "dist"]

