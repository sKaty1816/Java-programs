#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

const int SIZE_NAME = 23;

struct Car
{
	char carName[SIZE_NAME];
	int carHorsePower;
};

struct CarOwner
{
	char carOwnerName[SIZE_NAME];
	int idNumber;
	int regNumber;
};

void addCarOwnerToFile(); // adds all car owners in the file db-save.dat
bool checkRegNumber(int); // checks if the car's registration number already exists
double avrgCarsHorsePower(const Car cars[]); // find average power of all cars
int mostPopularCar(); // finds most popular car
int ownersAdded(); // returns the number of car owners in the file db-save.dat
void addOwnerCarsToFile(const Car cars[]); // adds the most popular car, average power of all owned cars 
//and 1 line for each owner with name and combined power of owned cars in the file car-report.txt

int main()
{
	const Car cars[4] = {
		{ "Lambordgini Murcielago", 670 },
		{ "Mercedes-AMG", 503 },
		{ "Pagani Zonda R", 740 },
		{ "Bugatti Veyron", 1020 }
	};
	int choice = 0;
	cout << "Menu:" << "\n";
	cout << "1. Add new car owner." << endl
		<< "2. Check what is the average horse power of all cars." << endl
		<< "3. Check which is the most popular car." << endl
		<< "4. Generate car report file." << endl
		<< "0. Exit the application." << endl << endl;

	do {
		cin.clear();
		cout << "Enter your choice: ";
		cin >> choice;
		switch (choice) {
		case 1: {
					addCarOwnerToFile();
					cout << endl;
					break;
		}
		case 2: {
					cout << "The average horse power of all cars is: "
						<< avrgCarsHorsePower(cars) << endl << endl;
					break;
		}
		case 3: {
					cout << "The most popular car is: "
						<< cars[mostPopularCar()].carName << endl << endl;
					break;
		}
		case 4: {
					addOwnerCarsToFile(cars);
					cout << "The car-report file was generated successfully." << endl << endl;
					break;
		}
		case 0: {
					return 0;
					break;
		}
		}
	} while (!cin.good() || choice != 0);

	return 0;

}

void addCarOwnerToFile(){
	CarOwner Owner;
	char ownerName[SIZE_NAME];
	cout << "Enter the car owner name (less than 23): ";
	cin.get();
	cin.getline(ownerName, SIZE_NAME);
	strcpy(Owner.carOwnerName, ownerName);

	int regNum = 0;
	cout << "Enter the registration car number: ";
	cin >> regNum;
	Owner.regNumber = regNum;
	if (checkRegNumber(regNum)) {
		cerr << "ERROR! The registration car number exist!" << endl;
		return;
	}

	int idNum = 0;
	cout << "Enter the identification car number (0, 1, 2, 3): ";
	cin >> idNum;
	if (idNum > 3 || idNum < 0) {
		cerr << "ERROR! Invalid indentification car number." << endl;
		return;
	}
	Owner.idNumber = idNum;

	ofstream myFile("db-save.dat", ios::binary | ios::app);
	if (!myFile) {
		cerr << "Error!" << endl;
		return;
	}
	myFile.write((const char*)&Owner, sizeof(CarOwner));
	myFile.close();
}

bool checkRegNumber(int idNum) {
	bool isNumExist = false;
	ifstream myFile("db-save.dat", ios::binary);
	CarOwner Owner;
	if (!myFile) {
		cerr << "Error!" << endl;
		exit;
	}

	do {
		myFile.read((char*)&Owner, sizeof(CarOwner));
		if (myFile) {
			if (idNum == Owner.regNumber) {
				return true;
			}
		}
	} while (myFile);
	return isNumExist;
}

double avrgCarsHorsePower(const Car cars[]) {
	ifstream myFile("db-save.dat", ios::binary);
	CarOwner Owner;
	if (!myFile) {
		cerr << "Error!" << endl;
		exit;
	}
	int counterCars = 0;
	int totalHorsePower = 0;
	int currentIdCar;
	do {
		myFile.read((char*)&Owner, sizeof(CarOwner));
		if (myFile) {
			counterCars += 1;
			currentIdCar = Owner.idNumber;
			totalHorsePower += cars[currentIdCar].carHorsePower;
		}
	} while (myFile);

	if (counterCars == 0) {
		return 0;
	}

	return totalHorsePower / counterCars;
}

int mostPopularCar() {

	ifstream myFile("db-save.dat", ios::binary);
	CarOwner Owner;
	if (!myFile) {
		cerr << "Error!" << endl;
		exit;
	}
	int popularCar[4] = { 0, 0, 0, 0 }; // araay that saves the number of encounters of certain car in the file
	do {
		myFile.read((char*)&Owner, sizeof(CarOwner));

		if (myFile) {
			if (Owner.idNumber == 0) {
				popularCar[0] += 1;
			}
			if (Owner.idNumber == 1) {
				popularCar[1] += 1;
			}
			if (Owner.idNumber == 2) {
				popularCar[2] += 1;
			}
			if (Owner.idNumber == 3) {
				popularCar[3] += 1;
			}
		}
	} while (myFile);

	int temp = 0;
	int index = 0;

	// find most popular car
	for (int i = 0; i < 4; i++) {
		if (popularCar[i] > temp) {
			temp = popularCar[i];
			index = i;
		}
	}

	return index;
}

void addOwnerCarsToFile(const Car cars[]) {
	ofstream myTextFile("car-report.txt", ios::trunc);
	if (!myTextFile) {
		cerr << "Error!" << endl;
		return;
	}

	myTextFile << "The most popular car is: " << cars[mostPopularCar()].carName << endl;
	myTextFile << "The average horse power of all cars is: " << avrgCarsHorsePower(cars) << endl;

	int counterOwners = ownersAdded();
	char** ownersName = new char*[counterOwners]; // array with all car owners names

	for (int i = 0; i < counterOwners; i++) {
		ownersName[i] = new char[SIZE_NAME];
	}

	ifstream myFile("db-save.dat", ios::binary);
	CarOwner Owner;
	if (!myFile) {
		cerr << "Error!" << endl;
		exit;
	}

	int row = 0;
	bool isNameInOwnersName = false;
	do {
		myFile.read((char*)&Owner, sizeof(CarOwner));
		if (myFile) {
			isNameInOwnersName = false;

			for (int i = 0; i < counterOwners; i++) {
				// check if car owner's name is already in the array
				if (strcmp(Owner.carOwnerName, ownersName[i]) == 0) {
					isNameInOwnersName = true;
					break;
				}
			}
			// add car owner's name in the array if it wasn't already there
			if (isNameInOwnersName == false) {
				strcpy(ownersName[row], Owner.carOwnerName);
				row += 1;
			}
		}
	} while (myFile);

	int currentOwnerCarsHorsePower = 0;

	// put pointer at the beginning of file
	myFile.clear();
	myFile.seekg(0, std::ios::beg);

	for (int i = 0; i < row; i++) {
		currentOwnerCarsHorsePower = 0;
		myFile.clear();
		myFile.seekg(0, ios::beg);
		do {
			myFile.read((char*)&Owner, sizeof(CarOwner));

			if (myFile) {
				// check if car owner's name is in the file
				// wether or not a person has more than one car and sums horse power of all his cars
				if (strcmp(Owner.carOwnerName, ownersName[i]) == 0) {
					currentOwnerCarsHorsePower += cars[Owner.idNumber].carHorsePower;
				}
			}
		} while (myFile);
		myTextFile << ownersName[i] << ' ' << currentOwnerCarsHorsePower << endl;
	}

	myTextFile.close();
	myFile.close();
}

int ownersAdded() {
	int counterOwners = 0;
	ifstream myFile("db-save.dat", std::ios::binary);
	if (!myFile) {
		cerr << "Error!" << endl;
		exit;
	}
	CarOwner Owner;
	int counterBytes = 0;
	do {
		myFile.read((char*)&Owner, sizeof(CarOwner));
		if (myFile) {
			counterOwners += 1;
		}
	} while (myFile);
	myFile.close();
	return counterOwners;
}