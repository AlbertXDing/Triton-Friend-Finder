"use client";
import React, { useState, CSSProperties  } from 'react';
import { useRouter } from 'next/navigation'; // Assuming you are using Next.js for routing

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>): Promise<void> => {
    e.preventDefault(); // Prevent page reload

    // Create the request payload
    const loginData = {
      email: email,
      password: password,
    };

    const credentials = btoa(`${email}:${password}`);

    console.log(loginData);
    try {
      const response = await fetch('http://localhost:8080/api/v1/users/signin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Basic ${credentials}`,
        },
        body: JSON.stringify(loginData),
      });

      console.log(response.ok);
      if (response.ok) {
        console.log('response ok');
        const data = await response.json();
        console.log(data); // This contains the JWT token and success message
        alert(data.message); // Show success message

        // Save the token to local storage for future authenticated requests
        localStorage.setItem('token', data.jwt);

        router.push('/lookup'); // Redirect to the lookup page
      } else {
        console.log('response not ok');
        const errorData = await response.json();
        alert(`Login failed: ${errorData.message}`);
      }
    } catch (error) {
      console.error('Error logging in:', error);
      alert('An error occurred. Please try again.');
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.formContainer}>
        <h1 style={styles.title}>Welcome to Triton Friend Finder!</h1>
        <form style={styles.form} onSubmit={handleSubmit}>
          <div style={styles.formGroup}>
            <label htmlFor="email" style={styles.label}>Email:</label>
            <input
              style={styles.input}
              type="email"
              id="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div style={styles.formGroup}>
            <label htmlFor="password" style={styles.label}>Password:</label>
            <input
              style={styles.input}
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" style={styles.submitButton}>Login</button>
          <p style={styles.signUpPrompt}>
            Don't have an account? <a href="/register" style={styles.link}>Create one here</a>
          </p>
        </form>
      </div>
      <div style={styles.illustrationContainer}>
        <img
          src="https://ih1.redbubble.net/image.5269424120.7476/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg"
          alt="Campus Connect"
          style={styles.illustration}
        />
        <p style={styles.illustrationText}>
          Discover, connect, and explore your campus.
        </p>
      </div>
    </div>
  );
}

const styles: { [key: string]: CSSProperties } = {
  container: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    height: '100vh',
    backgroundColor: '#f3f4f6',
    padding: '0 50px',
  },
  formContainer: {
    width: '40%',
    textAlign: 'center',
    backgroundColor: '#ffffff',
    padding: '30px',
    borderRadius: '10px',
    boxShadow: '0 4px 20px rgba(0, 0, 0, 0.1)',
  },
  title: {
    fontSize: '2em',
    marginBottom: '20px',
    color: '#006400',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
  },
  formGroup: {
    marginBottom: '15px',
  },
  label: {
    marginBottom: '5px',
    display: 'block',
    color: '#333',
  },
  input: {
    padding: '10px',
    borderRadius: '5px',
    border: '1px solid #cccccc',
    fontSize: '1em',
    width: '100%',
  },
  submitButton: {
    padding: '15px',
    marginTop: '20px',
    borderRadius: '5px',
    border: 'none',
    backgroundColor: '#32cd32',
    color: '#ffffff',
    fontSize: '1em',
    cursor: 'pointer',
  },
  signUpPrompt: {
    marginTop: '20px',
    color: '#006400',
  },
  link: {
    color: '#1e90ff',
    textDecoration: 'none',
  },
  illustrationContainer: {
    width: '40%',
    textAlign: 'center',
  },
  illustration: {
    width: '50%',
    marginBottom: '30px',
  },
  illustrationText: {
    fontSize: '1.2em',
    color: '#006400',
  },
};

export default LoginPage;
