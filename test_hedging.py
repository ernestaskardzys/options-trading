import unittest

from hedging import delta_hedging, hedge_with_puts_against_market_index, hedge_with_calls_against_market_index

class TestHedging(unittest.TestCase):
  def test_hedge_with_puts_against_market_index_with_positive_delta(self):
    full, rounded = hedge_with_puts_against_market_index(
       portfolio_value=720000, value_of_index_point=100, index_value=180, delta=0.6
    )
    self.assertAlmostEqual(full, 66.666666667, places=2)
    self.assertAlmostEqual(rounded, 67, places=2)
  
  def test_hedge_with_puts_against_market_index_with_negative_delta(self):
    full, rounded = hedge_with_puts_against_market_index(
       portfolio_value=720000, value_of_index_point=100, index_value=180, delta=-0.6
    )
    self.assertAlmostEqual(full, 66.666666667, places=2)
    self.assertAlmostEqual(rounded, 67, places=2)

  def test_hedge_with_calls_against_market_index(self):
    full, rounded = hedge_with_calls_against_market_index(
       portfolio_value=720000, value_of_index_point=100, index_value=180, delta=0.4
    )
    self.assertAlmostEqual(full, 100, places=2)
    self.assertAlmostEqual(rounded, 100, places=2)

  def test_delta_hedging_with_positive_delta(self):
    full, rounded = delta_hedging(number_of_shares=10000, delta=0.47)
    self.assertAlmostEqual(full, 212.77, places=2)
    self.assertAlmostEqual(rounded, 213, places=2)
  
  def test_delta_hedging_with_negative_delta(self):
    full, rounded = delta_hedging(number_of_shares=10000, delta=-0.47)
    self.assertAlmostEqual(full, 212.77, places=2)
    self.assertAlmostEqual(rounded, 213, places=2)
  

if __name__ == '__main__':
    unittest.main()