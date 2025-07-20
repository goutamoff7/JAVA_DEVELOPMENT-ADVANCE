document.getElementById('weatherForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting the traditional way

    const cityName = document.getElementById('cityName').value;

    if (cityName) {
        // Fetch weather data from your API
        fetch(`http://localhost:8080/weather?cityName=${cityName}`)
            .then(response => response.json())
            .then(data => {
                // Store the data in localStorage to use in view.html
                localStorage.setItem('weatherData', JSON.stringify(data));
                // Redirect to view.html
                window.location.href = 'view.html';
            })
            .catch(error => {
                console.error('Error fetching weather data:', error);
            });
    } else {
        alert('Please enter a city name.');
    }
});
