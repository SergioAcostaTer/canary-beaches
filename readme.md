---

## 🌊 **Canary Beaches API** 🏝️

📍 **Find and explore the best beaches in the Canary Islands!**

🔗 **Public Endpoint:** [https://beachesapi.canarycode.es/api/](https://beachesapi.canarycode.es/api/)

---

## 📖 **Table of Contents**
- 🌟 [Introduction](#-introduction)
- 🚀 [Endpoints](#-endpoints)
- 🔍 [Search & Filtering](#-search--filtering)
- 📌 [Pagination & Sorting](#-pagination--sorting)
- 💡 [Example Requests](#-example-requests)
- 🔧 [Error Handling](#-error-handling)
- 🛠 [Setup & Deployment](#-setup--deployment)

---

## 🌟 **Introduction**
Welcome to the **Canary Beaches API**, your go-to source for discovering beautiful beaches in the Canary Islands. 🏝️ This REST API allows you to:
✅ **Search** for beaches  
✅ **Filter** by location and name  
✅ **Find nearby beaches** based on your GPS coordinates  
✅ **Get a random beach** for a spontaneous adventure!

---

## 🚀 **Endpoints**

### 📍 **Get Beach by ID**
Retrieve detailed information about a specific beach by its ID.  
**📌 URL:** `GET /v1/beaches/{id}`

📥 **Request Example:**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/123"
```

📤 **Response Example:**
```json
{
    "id": 123,
    "name": "Playa del Inglés",
    "island": "Gran Canaria",
    "province": "Las Palmas",
    "municipality": "San Bartolomé de Tirajana",
    "latitude": 27.7566,
    "longitude": -15.5693
}
```

---

### 🔍 **Search for Beaches**
Search for beaches by name with pagination.  
**📌 URL:** `GET /v1/beaches/search`

📥 **Request Example:**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/search?query=El%20Burro&page=0&size=5"
```

📤 **Response Example:**
```json
{
    "content": [
        {
            "id": 722,
            "name": "Playa El Burro",
            "island": "Tenerife",
            "province": "Santa Cruz de Tenerife",
            "municipality": "Santa Cruz de Tenerife",
            "latitude": 28.5195,
            "longitude": -16.1641
        }
    ],
    "page": 0,
    "size": 5,
    "totalPages": 1,
    "totalElements": 1
}
```

---

### 🌍 **Find Nearby Beaches**
Get a list of beaches closest to your location within a given radius (in **km**).  
**📌 URL:** `GET /v1/beaches/nearby`

📥 **Request Example:**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/nearby?latitude=28.5&longitude=-16.2&radius=3&page=0&size=5"
```

📤 **Response Example:**
```json
{
    "content": [
        {
            "id": 723,
            "name": "Playa El Balayo",
            "island": "Tenerife",
            "province": "Santa Cruz de Tenerife",
            "municipality": "Santa Cruz de Tenerife",
            "latitude": 28.5183,
            "longitude": -16.1676,
            "distance": 2.16
        }
    ],
    "page": 0,
    "size": 5,
    "totalPages": 8,
    "totalElements": 36
}
```

---

### 🎲 **Get a Random Beach**
Fetch a random beach from the database (optionally filter by island).  
**📌 URL:** `GET /v1/beaches/random`

📥 **Request Example:**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/random?island=Tenerife"
```

📤 **Response Example:**
```json
{
    "id": 555,
    "name": "Playa de Benijo",
    "island": "Tenerife",
    "province": "Santa Cruz de Tenerife",
    "municipality": "Santa Cruz de Tenerife",
    "latitude": 28.5724,
    "longitude": -16.1766
}
```

---

## 📌 **Pagination & Sorting**
✅ All **search** and **nearby** endpoints support **pagination** and **sorting**.  
**Pagination Parameters:**
- `page` (default: `0`) – Which page to retrieve.
- `size` (default: `10`) – Number of results per page.
- `sortBy` (default: `"name"`) – Field to sort by.
- `direction` (default: `"asc"`) – Sort order (`asc` or `desc`).

📥 **Example Request with Sorting:**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/search?query=Playa&page=0&size=5&sortBy=name&direction=asc"
```

---

## 💡 **Example Requests**
### ✅ **Get Beaches in Tenerife Sorted by Distance**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/nearby?latitude=28.5&longitude=-16.2&radius=5&sortBy=distance&direction=asc"
```

### ✅ **Find a Random Beach in Gran Canaria**
```bash
curl -X GET "https://beachesapi.canarycode.es/api/v1/beaches/random?island=Gran Canaria"
```

---

## 🔧 **Error Handling**
The API returns **structured error responses**:

📤 **Example Error Response (Beach Not Found):**
```json
{
    "status": 404,
    "message": "Beach not found with id: 999"
}
```

📤 **Example Error Response (Invalid Sorting Parameter):**
```json
{
    "status": 400,
    "message": "Invalid sorting direction. Use 'asc' or 'desc'."
}
```

---

## 🛠 **Setup & Deployment**
1. **Clone the repo**
   ```bash
   git clone https://github.com/yourrepo/beaches-api.git
   cd beaches-api
   ```

2. **Run the API**
   ```bash
   mvn spring-boot:run
   ```

3. **Test locally**  
   Open your browser or use Postman:
   ```
   http://localhost:8080/v1/beaches/nearby?latitude=28.5&longitude=-16.2&radius=3
   ```

---

## 🚀 **Contributing**
We welcome contributions!
1. **Fork the repo**
2. **Create a feature branch**
3. **Submit a PR** 🎉

---

## 🎤 **Contact & Support**
For any questions, contact us at **support@canarycode.es** 📧

---

### 🌊 **Enjoy exploring the Canary Beaches with our API! 🏝️**