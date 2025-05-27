import React, { useState } from 'react';
import axios from 'axios';

const PaymentButton = ({ productName, productDescription, amount, currency = 'usd', buttonText = 'Pay Now' }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const handlePayment = async () => {
    setIsLoading(true);
    setError(null);
    
    try {
      // Call the backend API to generate a payment link
      const response = await axios.post('http://localhost:8080/api/payments/create-payment-link', {
        name: productName,
        description: productDescription,
        amount: amount, // Amount in cents
        currency: currency
      });
      
      // If successful, redirect to the payment URL
      if (response.data.success && response.data.paymentUrl) {
        window.location.href = response.data.paymentUrl;
      } else {
        setError('Failed to create payment link');
      }
    } catch (err) {
      setError(err.response?.data?.message || 'Error connecting to payment service');
      console.error('Payment error:', err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="payment-button-container">
      <button 
        onClick={handlePayment} 
        disabled={isLoading}
        className="payment-button"
      >
        {isLoading ? 'Processing...' : buttonText}
      </button>
      
      {error && <div className="payment-error">{error}</div>}
      
      <style jsx>{`
        .payment-button {
          background-color: #635bff;
          color: white;
          padding: 12px 24px;
          border-radius: 4px;
          border: none;
          font-size: 16px;
          cursor: pointer;
          transition: background-color 0.3s;
        }
        
        .payment-button:hover {
          background-color: #524dda;
        }
        
        .payment-button:disabled {
          background-color: #a5a5a5;
          cursor: not-allowed;
        }
        
        .payment-error {
          color: #e74c3c;
          margin-top: 10px;
          font-size: 14px;
        }
      `}</style>
    </div>
  );
};

export default PaymentButton;

// Example usage:
// <PaymentButton 
//   productName="Premium Subscription" 
//   productDescription="1 month access to premium content" 
//   amount={2500} // $25.00 in cents
//   buttonText="Subscribe Now"
// /> 