insert into Species (name) values ('Canine');
insert into Species (name) values ('Feline');

insert into Breed (name, speciesId) values ('Whippet', 1);
insert into Breed (name, speciesId) values ('Rat Terrier', 1);
insert into Breed (name, speciesId) values ('Jack Russell Terrier', 1);

insert into Breed (name, speciesId) values ('Domestic Shorthair', 2);

insert into Doctor (firstName, lastName) values ('Dwight' , 'Howard');
insert into Doctor (firstName, lastName) values ('Rashad' , 'Lewis');
insert into Doctor (firstName, lastName) values ('Vince' , 'Carter');
insert into Doctor (firstName, lastName) values ('Mikael' , 'Pietrus');

insert into Client (firstName, lastName, phone, street, apartment, city, state, zip, country) values ('Keith', 'Donald', '1-205-333-5555', '1234 Nasa Ave', '', 'Melbourne', 'FL', '32901', 'USA');
insert into Client (firstName, lastName, phone, street, apartment, city, state, zip, country) values ('Keri', 'Donald', '1-205-333-5555', '1234 Nasa Ave', '', 'Melbourne', 'FL', '32901', 'USA');

insert into Patient (name, gender, species, breed, birthDate, weight, clientId, doctorId) values ('Macy', 'F', 'Canine', 'Whippet', '2000-05-16', 25.4, 1, 1);
insert into Patient (name, gender, species, breed, birthDate, weight, clientId, doctorId) values ('Lil Jerry', 'M', 'Feline', 'Domestic Shorthair', '1998-04-01', 25.4, 2, 2);

insert into Appointment (dateTime, patientId, doctorId, reason) values ('2010-02-16 8:00:00', 1, 1, 'Annual checkup');
insert into Appointment (dateTime, patientId, doctorId, reason) values ('2010-02-16 16:00:00', 2, 2, 'Teeth cleaning');
