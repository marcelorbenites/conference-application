FROM node:12.14.1-alpine as NODE
WORKDIR /app
COPY . ./
RUN yarn
RUN yarn build

FROM node:12.14.1-alpine as BUILD
RUN yarn global add serve
WORKDIR /app
COPY --from=NODE /app/build ./build

EXPOSE 3000

ENTRYPOINT serve -s /app/build -l 3000
