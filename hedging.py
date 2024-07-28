def hedge_with_puts_against_market_index(portfolio_value, value_of_index_point, index_value, delta):
  """
  Calculate number of index option puts to buy for a full hedge.
  Usually ATM or ITM puts are used.

  Parameters:
  portfolio_value (float): Value of a portfolio
  value_of_index_point (float): Value of index point
  index_value (float): Value of an index
  delta (float): Delta of an option

  Returns:
  float, float: Full put value, rounded put value to the nearest number
  """
  full_result = portfolio_value / (value_of_index_point * index_value) / abs(delta)
  return full_result, round(full_result, ndigits=0)

def hedge_with_calls_against_market_index(portfolio_value, value_of_index_point, index_value, delta):
  """
  Calculate number of index option calls to sell for a full hedge.

  Parameters:
  portfolio_value (float): Value of a portfolio
  value_of_index_point (float): Value of index point
  index_value (float): Value of an index
  delta (float): Delta of an option

  Returns:
  float, float: Full call value, rounded put value to the nearest number
  """
  full_result = portfolio_value / (value_of_index_point * index_value) / abs(delta)
  return full_result, round(full_result, ndigits=0)

def delta_hedging(number_of_shares, delta):
  """
  Calculate number of ATM put options to buy to fully hedge your position.
  NOTE: As there are 100 shares per option contract, we divide it by 100.

  Parameters:
  number_of_shares (float): Number of shares of a specific stock
  delta (float): Delta of an option

  Returns:
  float, float: Full put amount, rounded put value to the nearest number
  """
  full_result = number_of_shares / abs(delta) / 100
  return full_result, round(full_result, ndigits=0)
