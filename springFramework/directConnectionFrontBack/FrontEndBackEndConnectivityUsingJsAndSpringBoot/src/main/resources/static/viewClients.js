document.addEventListener("DOMContentLoaded", async () => {
    const clientList = document.getElementById("clientList");

    try {
        let response = await fetch("http://localhost:8080/getClients");

        if (!response.ok) {
            throw new Error(`Failed to fetch clients. Status: ${response.status}`);
        }

        let clients = await response.json();
        console.log("Fetched clients list:", clients);

        clients.forEach(client => {
            let row = document.createElement("tr");

            let idCell = document.createElement("td");
            idCell.textContent = client.id;
            row.appendChild(idCell);

            let nameCell = document.createElement("td");
            nameCell.textContent = client.name;
            row.appendChild(nameCell);

            clientList.appendChild(row);
        });
    } catch (error) {
        console.error("Error occurred:", error.message);
    }
});
