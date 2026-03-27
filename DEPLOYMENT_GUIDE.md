# Deployment Guide for Render

## Overview
This project consists of a Spring Boot backend and a React/Vite frontend. Both will be deployed to Render.

## Prerequisites
- GitHub account with your repository
- Render account (https://render.com)
- Your project pushed to GitHub

## Steps to Deploy

### 1. Push to GitHub
```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git push -u origin main
```

### 2. Connect to Render
1. Go to [Render Dashboard](https://dashboard.render.com)
2. Click **"New +"** → **"Blueprint"**
3. Connect your GitHub repository
4. Click **"Connect"** on your repository

### 3. Render Will Automatically Deploy
- Render reads `render.yaml` and deploys both services
- Backend builds with Maven
- Frontend builds with Node.js
- PostgreSQL database is created automatically

### 4. Monitor Deployment
1. Go to the **Blueprint** page in Render Dashboard
2. Click on each service to see logs
3. Wait for both services to show "Live" status

### 5. Configure CORS (if needed)
If your frontend can't reach the backend, update `SecurityConfig.java`:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(
            "https://YOUR_FRONTEND_URL.onrender.com",
            "http://localhost:3000"
        ));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
```

### 6. Get Your URLs
After deployment completes:
- **Backend**: `https://sales-backend-xxxxx.onrender.com`
- **Frontend**: `https://sales-frontend-xxxxx.onrender.com`

Update your frontend `AuthService.js`, `ProductService.js`, and `SalesService.js` with the backend URL.

### 7. Database Management
- PostgreSQL database is automatically created
- Access credentials in Render Dashboard → Database → Connection string
- Data persists in PostgreSQL (not in-memory like H2)

## Troubleshooting

### Build fails
- Check logs in Render Dashboard
- Ensure all Maven dependencies are correct
- Verify Node.js dependencies in package.json

### Frontend can't reach backend
- Check CORS configuration
- Verify backend URL in frontend service files
- Check firewall/security rules

### Database connection errors
- Verify `DATABASE_URL` environment variable is set
- Check database username and password
- Ensure PostgreSQL dialect is used (done in application.properties)

## Environment Variables
The following variables are automatically set by Render:
- `DATABASE_URL`: PostgreSQL connection string
- `DB_USERNAME`: Database user
- `DB_PASSWORD`: Database password
- `PORT`: Service port

## Updating After Deployment
1. Push changes to GitHub
2. Render automatically rebuilds and deploys
3. Monitor in Render Dashboard

## Notes
- First time deployment may take 5-10 minutes
- Builds and previews are limited on free tier
- Consider upgrading to paid plan for production use
