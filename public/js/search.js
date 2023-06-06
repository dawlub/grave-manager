const search = document.querySelector('input[placeholder="Search relatives"]');
const relativeContainer = document.querySelector(".relatives")

search.addEventListener("keyup", function (event){
    if (event.key === "Enter") {
        event.preventDefault();
        console.log("start");
        const data = {search: this.value};

        fetch("/search", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (response) {
            console.log(response);
            return response.json();
        }).then(function (relatives) {
            relativeContainer.innerHTML = "";
            loadRelatives(relatives)
        });
    }
});

function loadRelatives(relatives) {
    relatives.forEach(relative => {
        console.log(relative);
        createRelative(relative);
    })
}

function createRelative(relative) {
    const template = document.querySelector("#relative-template");

    const clone = template.content.cloneNode(true);
    console.log(relative);
    const image = clone.querySelector("img");
    image.src = `/public/uploads/${relative.image}`;
    console.log(image);
    const fullName = clone.querySelector('h2[name="fullName"]');
    fullName.innerHTML = relative.full_name;

    const dateOfBirth = clone.querySelector('span[name="dateOfBirth"]')
    dateOfBirth.innerHTML = relative.date_of_birth;

    const dateOfDeath = clone.querySelector('span[name="dateOfDeath"]')
    dateOfDeath.innerHTML = relative.date_of_death;

    const location = clone.querySelector('span[name="location"]');
    location.innerHTML = relative.location;


    relativeContainer.appendChild(clone);
}