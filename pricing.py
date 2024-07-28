import math
from scipy.stats import norm

def black_scholes(S, K, T, r, sigma, option_type='call'):
  """
  Calculate the Black-Scholes option price for a European call or put option.

  Parameters:
  S (float): Current stock price
  K (float): Option strike price
  T (float): Time to maturity in years
  r (float): Risk-free interest rate
  sigma (float): Volatility of the stock
  option_type (str): Type of the option - 'call' or 'put'

  Returns:
  float: Theoretical price of the option
  """
  # Calculate d1 and d2
  d1 = (math.log(S / K) + (r + 0.5 * sigma ** 2) * T) / (sigma * math.sqrt(T))
  d2 = d1 - sigma * math.sqrt(T)

  if option_type == 'call':
      option_price = S * norm.cdf(d1) - K * math.exp(-r * T) * norm.cdf(d2)
  elif option_type == 'put':
      option_price = K * math.exp(-r * T) * norm.cdf(-d2) - S * norm.cdf(-d1)
  else:
      raise ValueError("Invalid option type. Use 'call' or 'put'.")

  return option_price
