import styles from "./page.module.css";

export default function Login() {
  return (
    <main className={styles.main}>
      <div className={styles.description}>
        <form className={styles.form}>
          <div className={styles.formGroup}>
            <label className={styles.question}>Welcome to Triton Friend Finder!</label>
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="email" className={styles.question}>Email:</label>
            <input type="email" id="email" name="email" required />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="password" className={styles.question}>Password:</label>
            <input type="password" id="password" name="password" required />
          </div>
          <button type="submit" className={`${styles.submitButton} ${styles.buttonSpacing}`}>Login</button>
          <div className={styles.questionLink}>
            <span>Don't have an account? <a href="/register" className={styles.link}>Create one here</a></span>
          </div>
        </form>
      </div>
    </main>
  );
}
