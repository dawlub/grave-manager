const search = document.querySelector('input[placeholder="Search relatives"]');
const relativeContainer = document.querySelector(".relatives")

search.addEventListener("keyup", function (event){
    if (event.key === "Enter") {
        event.preventDefault();
        const data = {search: this.value};

        fetch("/search", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (response) {
            return response.json();
        }).then(function (relatives) {
            relativeContainer.innerHTML = "";
            loadRelatives(relatives)
        });
    }
});

function loadRelatives(relatives) {
    relatives.forEach(relative => {
        createRelative(relative);
    })
}

function createRelative(relative) {
    const template = document.querySelector("#relative-template");

    const clone = template.content.cloneNode(true);

    const image = clone.querySelector("img");
    image.src = `/public/uploads/${relative.image}`;

    const fullName = clone.querySelector('h2[name="fullName"]');
    fullName.innerHTML = relative.full_name;

    const dateOfBirth = clone.querySelector('span[name="dateOfBirth"]')
    dateOfBirth.innerHTML = relative.date_of_birth;

    const dateOfDeath = clone.querySelector('span[name="dateOfDeath"]')
    dateOfDeath.innerHTML = relative.date_of_death;

    const location = clone.querySelector('span[name="location"]');
    location.innerHTML = relative.location;

    const addToCollection = clone.querySelector('.add-searched');
    addToCollection.addEventListener('click', function (event) {
        event.stopPropagation();
        console.log(relative.id)
        fetch("/addToCollection", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id: relative.id})
        }).then(function (response) {
            return response.json();
        }).then(function (data) {
            // Handle the response from the server
        });
    });
    relativeContainer.appendChild(clone);
}