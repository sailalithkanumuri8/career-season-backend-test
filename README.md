# Career Season Backend Test

Integrates Stripe and Supabase into the test backend.

## Backend Setup

1. **Configure Stripe API key**:
   Edit `src/main/resources/application.properties` and replace `YOUR_STRIPE_API_KEY_HERE` with actual Stripe test API key.

   ```properties
   stripe.api.key=YOUR_STRIPE_API_KEY_HERE
   ```

2. **Configure Supabase Database**:
   Edit `src/main/resources/application.properties` and replace `YOUR_SUPABASE_PASSWORD_HERE` with your actual Supabase database password.

   ```properties
   spring.datasource.password=YOUR_SUPABASE_PASSWORD_HERE
   ```

3. **Start the Spring Boot application**:
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints

### Stripe Payment Links

- **Create payment link**: `POST /api/payments/create-payment-link`
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "amount": 2500,
    "currency": "usd"
  }
  ```

### Blogs

- **Get all blogs**: `GET /api/blogs`
- **Get visible blogs only**: `GET /api/blogs?visibleOnly=true`
- **Get blog by ID**: `GET /api/blogs/{id}`
- **Get blog by textId**: `GET /api/blogs/textId/{textId}`
- **Get blogs by site**: `GET /api/blogs/site/{site}`
- **Filter blogs**: `GET /api/blogs/filter` with query parameters:
  - `keyword`: Search in title, content and description
  - `author`: Filter by author name
  - `site`: Filter by site name
- **Create blog**: `POST /api/blogs`
- **Update blog**: `PUT /api/blogs/{id}`
- **Delete blog**: `DELETE /api/blogs/{id}`

### Positions

- **Get all positions**: `GET /api/positions`
- **Get visible positions only**: `GET /api/positions?visibleOnly=true`
- **Get position by ID**: `GET /api/positions/{id}`
- **Filter positions**: `GET /api/positions/filter` with query parameters:
  - `status`: Filter by status (e.g., "notOpen", "closed")
  - `site`: Filter by site (e.g., "apm")
  - `jobType`: Filter by job type (e.g., "internship", "full-time")
  - `season`: Filter by season (e.g., "Summer:2026")
  - `visaSponsored`: Filter by visa sponsorship (e.g., "yes", "no")
  - `company`: Filter by company ID
  - `keyword`: Search in title and description
- **Create position**: `POST /api/positions`
- **Update position**: `PUT /api/positions/{id}`
- **Delete position**: `DELETE /api/positions/{id}`

## Frontend Integration

In React application, you can use the provided `PaymentButton` component as follows:

```jsx
import PaymentButton from './PaymentButton';

function App() {
  return (
    <div className="App">
      <h1>Product Checkout</h1>
      <PaymentButton 
        productName="Premium Subscription" 
        productDescription="1 month access to premium content" 
        amount={2500} // $25.00 in cents
        buttonText="Subscribe Now"
      />
    </div>
  );
}
```