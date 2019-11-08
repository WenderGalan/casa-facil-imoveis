FROM node:lts-alpine as build-stage
# Faz da pasta atual o diretorio
WORKDIR /app
# Copia os arquivos do package.json e package-lock.json (se disponivel)
COPY package*.json ./
# Instala dependências do projeto
RUN npm install
# Copia arquivos e pastas para o directorio atual de trabalho
COPY . .
# Compila a aplicação de produção
RUN npm run build

# estágio de produção
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
# Expõe a porta
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]