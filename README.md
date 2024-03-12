# Quick Start

1. Clone the repository.

```shell
git clone https://github.com/th1nk-er/reCAPTCHA-Enterprise-Demo.git
```

2. Replace your reCAPTCHA key

- `reCAPTCHA-Web/src/main.ts`

```typescript
app.config.globalProperties.$siteKey = "SITE KEY";
```

- `reCAPTCHA-Server/src/main/resources/application.yml`

```yaml
reCAPTCHA:
  # visit https://console.cloud.google.com/apis/credentials to get the api key
  api-key: "API KEY"
  # visit https://console.cloud.google.com/security/recaptcha to get the project id and site key
  project-id: " PROJECT ID"
  site-key: "SITE KEY"
```

3. Test reCAPTCHA

```shell
cd reCAPTCHA-Server
mvn package
java -jar target/reCAPTCHA-Server-0.0.1-SNAPSHOT.jar
cd ../reCAPTCHA-Web
pnpm install
pnpm dev
```

