# options-trading

Option pricing has been always a difficult subject to master, therefore I'm currently reading a great book `Options as a Strategic Investment` by Lawrence G. McMillan - [link on Amazon](https://www.amazon.com/Strategic-Investment-Lawrence-McMillan-Hardcover/dp/B071RJVMWY/)

To calculate various aspects of options, I could have used available online tools. But, as a software engineer, I want to write things by my own :)

Therefore, I wrote a couple of useful Python - based implementations for options:

- Black-Scholes formula
- Hedging calculations:
-- Delta hedging
-- Hedging porfolio with index put and call options

## Requirements

- Python 3

# Installation

Execute:

```bash
python3 -m venv venv
source venv/bin/activate

pip install -r requirements.txt
```