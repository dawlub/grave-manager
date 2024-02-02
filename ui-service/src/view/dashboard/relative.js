import React, { useState } from 'react';

const RelativeItem = ({ relative }) => {
    const [visitDate, setVisitDate] = useState(relative.visitDate || ''); // Add a default value if necessary

    const saveVisitDate = () => {
        // Implement saving logic here
        console.log('Saving new visit date:', visitDate, 'for relative ID:', relative.id);
        // Placeholder for fetch request to save the new visit date
    };

    return (
        <div className="relative">
            <div>
                <input
                    type="text"
                    value={visitDate}
                    onChange={(e) => setVisitDate(e.target.value)}
                    onBlur={saveVisitDate}
                />
            </div>
            <img src={relative.imageBase64 ? `data:image/jpeg;base64,${relative.imageBase64}` : 'defaultImagePath'} alt={`${relative.firstName} ${relative.lastName}`} />
            <div>
                <h2>{`${relative.firstName} ${relative.lastName}`}</h2>
                <ul>
                    <li>Date of Birth: {relative.dateOfBirth}</li>
                    <li>Date of Death: {relative.dateOfDeath}</li>
                    <li>Location: {relative.location}</li>
                </ul>
            </div>
        </div>
    );
};

export default RelativeItem;