import unittest
from pricing import black_scholes

class TestBlackScholes(unittest.TestCase):
    
  S = 100
  K = 100
  T = 1
  r = 0.05
  sigma = 0.2

  def test_call_option(self):
    option_type = 'call'
    expected_price = 10.45  # Pre-calculated expected price
    calculated_price = black_scholes(self.S,self.K, self.T, self.r, self.sigma, option_type)
    self.assertAlmostEqual(calculated_price, expected_price, places=2)

  def test_put_option(self):
    option_type = 'put'
    expected_price = 5.57  # Pre-calculated expected price
    calculated_price = black_scholes(self.S,self.K, self.T, self.r, self.sigma, option_type)
    self.assertAlmostEqual(calculated_price, expected_price, places=2)

  def test_invalid_option_type(self):
    option_type = 'not_existing_one'
    with self.assertRaises(ValueError):
      black_scholes(self.S,self.K, self.T, self.r, self.sigma, option_type)

if __name__ == '__main__':
    unittest.main()
