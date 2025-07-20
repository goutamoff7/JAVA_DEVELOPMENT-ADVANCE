const submit = document.querySelector('#submit');

const callBackendApi = async () => {
    let clientName = document.getElementById("name").value;

    try {
        let response2 = await fetch("http://localhost:8080/save", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: clientName })
        });

        if (!response2.ok) {
            throw new Error(`Failed to save client. Status: ${response2.status}`);
        }

        console.log("Client saved successfully:", await response2.text());

        // Redirect to viewClients.html after successful submission
        window.location.href = "viewClients.html";
    } catch (error) {
        console.error("Error occurred:", error.message);
    }
};

submit.addEventListener('click', callBackendApi);
