import random
from datetime import datetime, timedelta
import json
import requests

#connection to sql server did not work, so I created the test data here
CITIES_IN_EUROPE = [
    "Vienna, Austria", "Berlin, Germany", "Madrid, Spain",
    "Rome, Italy", "Paris, France", "Bucharest, Romania",
    "Budapest, Hungary", "Hamburg, Germany", "Warsaw, Poland",
    "Barcelona, Spain", "Munich, Germany", "Milan, Italy"
]
CHAINS = ["Spar", "Interspar", "Eurospar", "Spar Express"]
PRODUCT_NAMES = {
    "Electronics": ["Smartphone", "Laptop", "Headphones", "E-reader", "Smartwatch"],
    "Grocery": ["Organic Apples", "Whole Wheat Bread", "Almond Milk", "Free-Range Eggs", "Quinoa"],
    "Clothing": ["Cotton T-shirt", "Leather Jacket", "Denim Jeans", "Sneakers", "Silk Scarf"],
    "Household": ["LED Light Bulb", "Laundry Detergent", "Bath Towel", "Vacuum Cleaner", "Picture Frame"],
    "Toys": ["Lego Building Set", "Action Figure", "Board Game", "Plush Toy", "Puzzle"]
}


def random_date(start, end):

    delta = end - start
    int_delta = delta.days
    random_day = random.randrange(int_delta)
    return start + timedelta(days=random_day)


start_date = datetime.strptime("2022-01-01", "%Y-%m-%d")
end_date = datetime.strptime("2023-12-31", "%Y-%m-%d")

data = []
for i in range(1, 501):
    date_generated = random_date(start_date, end_date)

    city_country = random.choice(CITIES_IN_EUROPE).split(", ")
    city = city_country[0]
    country = city_country[1]

    category = random.choice(list(PRODUCT_NAMES.keys()))
    product_name = random.choice(PRODUCT_NAMES[category])
    quantity = random.randint(1, 10)
    original_price = round(random.uniform(10, 1000), 2)
    variation = round(random.uniform(-0.2, 0.2), 2)
    sold_price = round(original_price * (1 + variation),2)
    discount = round(random.uniform(0, 0.3), 2)
    final_price = round((sold_price *(1-discount))* quantity,2)
    entry = {
        "quantity": quantity,
        "discount": discount,
        "price": sold_price,
        "final_price": final_price,
        "time": {
            "date_day": date_generated.day,
            "date_month": date_generated.month,
            "date_year": date_generated.year,
            "date_time": date_generated.strftime("%Y-%m-%d")
        },
        "location": {
            "country": country,
            "city": city,
            "address": f"{random.randint(1, 1000)} {random.choice(['Main St', 'Market St', 'Park Ave'])}",
            "name": f"Spar Store Nr. {i}",
            "chain": random.choice(CHAINS)
        },
        "product": {
            "name": product_name,
            "price": original_price,
            "category": category
        }
    }
    data.append(entry)
with open('data.json', 'w') as f:
    json.dump(data, f, indent=2)

print(f"Generated {len(data)} entries in 'data.json'.")


def post_data(entry):
    response = requests.post("http://localhost:8080/sales/save", json=entry)
    if response.status_code == 200 or response.status_code == 201:
        print(f"Success: Entry  added.")
    else:
        print(f"Error: Failed to add entry  Status code: {response.status_code}, Response: {response.text}")

for entry in data:
    post_data(entry)




