# Grave Manager

Grave Manager is a comprehensive solution designed to manage and maintain records of graves, ensuring efficient management of cemeteries.

## Modules

- **Backend Service**: Manages the data, API calls, and business logic. [Read More](./backend-service/README.md)
- **UI Service**: Provides a user-friendly interface for interaction. [Read More](./ui-service/README.md)

## Getting Started

To get the entire Grave Manager solution up and running, follow the setup instructions in the README files for both the:
- [Backend Service](./backend-service/README.md) 
- [UI Service](./ui-service/README.md)

## Contributing

We welcome contributions to any part of the Grave Manager! Please ensure you read the README and contributing guidelines in each module/service you're interested in.

## Contact

For queries or feedback, reach out to us at `support@gravemanager.com`.


### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/GraveManager.git

## Run-infrastructure-locally

The infrastructure for grave-manager consist of:
- PostgresDB

1. Use the following command from the root folder to setup required infrastructure:
```shell
docker-compose up -d
```