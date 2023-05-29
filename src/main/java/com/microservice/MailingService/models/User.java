package com.microservice.MailingService.models;

public class User
{
        private String firstname;
        private String lastname;
        private int age;
        private String contact;
        public  User()
        {
        }


        public User(String firstname, String lastname, int age, String contact) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.contact = contact;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public int getage() {
            return age;
        }

        public void setage(int age) {
            this.age = age;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }



