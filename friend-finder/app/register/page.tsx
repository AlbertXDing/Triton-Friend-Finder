"use client";

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import styles from "../page.module.css";

const genders = [
  { id: "gender_male", value: "male", label: "Male" },
  { id: "gender_female", value: "female", label: "Female" },
  { id: "gender_other", value: "other", label: "Other" }
];

export default function Register() {
  const router = useRouter();
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    email: '',
    firstName: '',
    lastName: '',
    sex: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const addUser = async (e) => {
    e.preventDefault();
  
    try {
      const response = await fetch('/api/v1/registration', { // Ensure the correct API endpoint
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });
  
      if (!response.ok) {
        throw new Error(`Error creating user: ${response.statusText}`);
      }
  
      console.log('User created:', await response.json());
      router.push('/lookup'); // Redirect to the lookup page
    } catch (error) {
      console.error('Failed to create user:', error);
    }
  };

  return (
    <main className={styles.main}>
      <div className={styles.description}>
        <form className={styles.form} onSubmit={addUser}>
          <div className={styles.formGroup}>
            <label className={styles.question}>Triton Friend Finder</label>
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="username" className={styles.question}>Username:</label>
            <input type="text" id="username" name="username" value={formData.username} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="firstName" className={styles.question}>First Name:</label>
            <input type="text" id="firstName" name="firstName" value={formData.firstName} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="lastName" className={styles.question}>Last Name:</label>
            <input type="text" id="lastName" name="lastName" value={formData.lastName} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="email" className={styles.question}>Email:</label>
            <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="password" className={styles.question}>Password:</label>
            <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} required />
          </div>
          <div className={styles.formGroup}>
            <label className={styles.question}>Gender:</label>
            {genders.map(({ id, value, label }) => (
              <div key={id} className={styles.radioGroup}>
                <input type="radio" id={id} name="sex" value={value} onChange={handleChange} required />
                <label htmlFor={id} className={styles.answer}>{label}</label>
              </div>
            ))}
          </div>
          <button type="submit" className={styles.submitButton}>Submit</button>
        </form>
      </div>
    </main>
  );
}
