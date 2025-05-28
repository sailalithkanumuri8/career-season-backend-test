# Career Season Backend Test

Integrates Stripe and Supabase into the test backend.

## Backend Setup

1. **Configure Stripe API key**:
   Edit `src/main/resources/application.properties` and replace `YOUR_STRIPE_API_KEY_HERE` with actual Stripe test API key.

   ```properties
   stripe.api.key=YOUR_STRIPE_API_KEY_HERE
   ```

2. **Start the Spring Boot application**:
   ```bash
   ./mvnw spring-boot:run
   ```

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

## API Endpoints

### Create Payment Link

- **URL**: `/api/payments/create-payment-link`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "amount": 2500,
    "currency": "usd"
  }
  ```
- **Response**:
  ```json
  {
    "paymentUrl": "https://checkout.stripe.com/pay/cs_test_...",
    "success": true,
    "message": "Payment link created successfully"
  }
  ```