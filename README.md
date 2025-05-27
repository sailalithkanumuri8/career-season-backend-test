# Stripe Payment Link Integration

This project demonstrates how to generate Stripe payment links from a Spring Boot backend that can be used in a React frontend.

## Backend Setup

1. **Configure your Stripe API key**:
   Edit `src/main/resources/application.properties` and replace `YOUR_STRIPE_API_KEY_HERE` with your actual Stripe test API key.

   ```properties
   stripe.api.key=YOUR_STRIPE_API_KEY_HERE
   ```

   **IMPORTANT: Never commit your actual API keys to a repository. This is a security risk.**

2. **Start the Spring Boot application**:
   ```bash
   ./mvnw spring-boot:run
   ```

## Frontend Integration

In your React application, you can use the provided `PaymentButton` component as follows:

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

## Testing

1. Use test card numbers from Stripe's documentation:
   - `4242 4242 4242 4242` - Successful payment
   - `4000 0000 0000 0002` - Declined payment

2. Set a future expiration date and any CVC code.

## Important Notes

- This implementation uses Stripe's Payment Links, which are ideal for simple, one-time payment flows.
- For more complex payment flows (like subscriptions or payment methods that require customer authentication), consider using Stripe Checkout or Elements.
- Always use Stripe's test mode during development.
- In production, make sure to restrict CORS settings in the `PaymentController` to your specific frontend domains. 