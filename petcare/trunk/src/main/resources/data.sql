insert into Species (name) values ('Canine');
insert into Species (name) values ('Feline');

insert into Breed (name, speciesId) values ('Whippet', 1);
insert into Breed (name, speciesId) values ('Rat Terrier', 1);
insert into Breed (name, speciesId) values ('Jack Russell Terrier', 1);

insert into Breed (name, speciesId) values ('Domestic Shorthair', 2);

insert into Doctor (firstName, lastName) values ('Dwight' , 'Howard');
insert into Doctor (firstName, lastName) values ('Rasheed' , 'Lewis');
insert into Doctor (firstName, lastName) values ('Vince' , 'Carter');
insert into Doctor (firstName, lastName) values ('Mikael' , 'Pietrus');

insert into Client (firstName, lastName, phone, street, apartment, city, state, zip, country) values ('Keith', 'Donald', '1-205-333-5555', '1234 Nasa Ave', '', 'Melbourne', 'FL', '32901', 'USA');
insert into Client (firstName, lastName, phone, street, apartment, city, state, zip, country) values ('Keri', 'Donald', '1-205-333-5555', '1234 Nasa Ave', '', 'Melbourne', 'FL', '32901', 'USA');

insert into Patient (name, gender, species, breed, birthDate, weight, clientId, doctorId) values ('Macy', 'F', 'Canine', 'Whippet', '2000-05-16', 25.4, 1, 4);
insert into Patient (name, gender, species, breed, birthDate, weight, clientId, doctorId) values ('Lil Jerry Seinfield', 'M', 'Feline', 'Domestic Shorthair', '1998-04-01', 25.4, 2, 1);

insert into Appointment (dateTime, reason, patientId) values ('2009-10-23 08:30:00', 'Annual checkup', 1);
insert into Appointment (dateTime, reason, patientId) values ('2009-10-23 14:00:00', 'Teeth cleaning', 2);
