// Import Firebase modules
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.15.0/firebase-app.js";
import { getDatabase, ref, set, get } from "https://www.gstatic.com/firebasejs/9.15.0/firebase-database.js";

// Your Firebase configuration
const firebaseConfig = {
    apiKey: "YOUR_API_KEY",
    authDomain: "YOUR_PROJECT_ID.firebaseapp.com",
    databaseURL: "https://YOUR_PROJECT_ID.firebaseio.com",
    projectId: "YOUR_PROJECT_ID",
    storageBucket: "YOUR_PROJECT_ID.appspot.com",
    messagingSenderId: "YOUR_SENDER_ID",
    appId: "YOUR_APP_ID"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getDatabase(app);

// Function to save meal selection
function saveMeal() {
    const meal = document.getElementById("mealSelect").value;
    set(ref(db, "selectedMeal/"), { meal })
    .then(() => alert("Meal Saved Successfully!"))
    .catch(error => console.error("Error:", error));
}

// Function to set a constant (default) meal
function setDefaultMeal() {
    const constantMeal = document.getElementById("constantMeal").value;
    set(ref(db, "defaultMeal/"), { constantMeal })
    .then(() => alert("Default Meal Set!"))
    .catch(error => console.error("Error:", error));
}

// Function to retrieve last selected meal
function getLastMeal() {
    get(ref(db, "selectedMeal/"))
    .then(snapshot => {
        if (snapshot.exists()) {
            document.getElementById("lastMeal").innerText = "Last Selected: " + snapshot.val().meal;
        } else {
            document.getElementById("lastMeal").innerText = "No meal selected yet.";
        }
    })
    .catch(error => console.error("Error:", error));
}

// Load last selected meal when the page loads
window.onload = getLastMeal;
