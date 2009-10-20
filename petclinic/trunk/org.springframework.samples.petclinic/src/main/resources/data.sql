insert into Species (name) values ('Canine');
insert into Species (name) values ('Feline');

insert into Breed (name, speciesId) values ('Whippet', 1);
insert into Breed (name, speciesId) values ('Rat Terrier', 1);
insert into Breed (name, speciesId) values ('Jack Russell Terrier', 1);

insert into Breed (name, speciesId) values ('Domestic Shorthair', 2);

insert into Doctor (firstName, lastName) values ('Wesley' , 'Foster');
insert into Doctor (firstName, lastName) values ('Bob' , 'Evans');
insert into Doctor (firstName, lastName) values ('Martha' , 'Sugalski');
insert into Doctor (firstName, lastName) values ('Dwight' , 'Howard');

insert into Client (firstName, lastName, phone, street, apartment, city, state, zip, country, doctorId) values ('Keith', 'Donald', '1-205-333-5555', '1234 Nasa Ave', '', 'Melbourne', 'FL', '32901', 'USA', 4);
insert into Client (firstName, lastName, phone, street, apartment, city, state, zip, country, doctorId) values ('Keri', 'Donald', '1-205-333-5555', '1234 Nasa Ave', '', 'Melbourne', 'FL', '32901', 'USA', 1);

insert into Patient (name, gender, species, breed, birthDate, weight, clientId) values ('Macy', 'F', 'Canine', 'Whippet', '2000-05-16', 25.4, 1);
insert into Patient (name, gender, species, breed, birthDate, weight, clientId) values ('Lil Jerry Seinfield', 'M', 'Feline', 'Domestic Shorthair', '1998-04-01', 25.4, 2);

insert into Appointment (dateTime, notes, patientId) values ('2009-10-23 08:30:00', 'Checkup', 1);
insert into Appointment (dateTime, notes, patientId) values ('2009-10-23 14:00:00', 'Checkup', 2);
