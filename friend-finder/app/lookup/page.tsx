"use client";

import { useState, useEffect } from 'react';
import styles from "../page.module.css";

export default function UserPage() {
  const [user, setUser] = useState(null);

  const fetchUser = async () => {
    // const response = await fetch('/api/v1/users/test1');
    const response = await fetch('/api/v1/users');
    if (response.ok) {
      const data = await response.json();
      setUser(data[Math.floor(Math.random() * data.length)]); //Random user for now
    } else {
      console.error('Failed to fetch user data');
    }
  };

  useEffect(() => {
    fetchUser();
  }, []);

  if (!user) {
    return <p>Loading...</p>;
  }

  const { username, firstName, lastName, email, sex } = user;

  return (
    <main className={styles.main}>
      <div className={styles.userInfo}>
        <h1>{username}</h1>
        <div>First Name: {firstName}</div>
        <div>Last Name: {lastName}</div>
        <div>Email: {email}</div>
        <div>Gender: {sex}</div>
        <div className={styles.buttonContainer}>
            <button
            onClick={fetchUser}
            className={styles.matchButton}
            >
            Match
            </button>
            <button
            onClick={fetchUser}
            className={styles.nextButton}
            >
            Next
            </button>
        </div>
      </div>  
    </main>
  );
}
