import styles from "../page.module.css";

export default function Register() {
  return (
    <main className={styles.main}>
      <div className={styles.description}>
        <form className={styles.form}>
          <div className={styles.formGroup}>
            <label className={styles.question}>Triton Friend Finder</label>
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="name" className={styles.question}>Name:</label>
            <input type="text" id="name" name="name" required />
          </div>
          <div className={styles.formGroup}>
            <label htmlFor="email" className={styles.question}>Email:</label>
            <input type="email" id="email" name="email" required />
          </div>
          <div className={styles.formGroup}>
            <label className={styles.question}>Gender:</label>
            <div className={styles.radioGroup}>
              <input type="radio" id="gender_male" name="gender" value="male" required />
              <label htmlFor="gender_male" className={styles.answer}>Male</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="gender_female" name="gender" value="female" required />
              <label htmlFor="gender_female" className={styles.answer}>Female</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="gender_other" name="gender" value="other" required />
              <label htmlFor="gender_other" className={styles.answer}>Other</label>
            </div>
          </div>
          <div className={styles.formGroup}>
            <label className={styles.question}>Which college are you in?</label>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_revelle" name="college" value="revelle" required />
              <label htmlFor="college_revelle" className={styles.answer}>Revelle</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_john_muir" name="college" value="john_muir" required />
              <label htmlFor="college_john_muir" className={styles.answer}>John Muir</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_thurgood_marshall" name="college" value="thurgood_marshall" required />
              <label htmlFor="college_thurgood_marshall" className={styles.answer}>Thurgood Marshall</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_earl_warren" name="college" value="earl_warren" required />
              <label htmlFor="college_earl_warren" className={styles.answer}>Earl Warren</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_eleanor_roosevelt" name="college" value="eleanor_roosevelt" required />
              <label htmlFor="college_eleanor_roosevelt" className={styles.answer}>Eleanor Roosevelt</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_sixth" name="college" value="sixth" required />
              <label htmlFor="college_sixth" className={styles.answer}>Sixth</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_seventh" name="college" value="seventh" required />
              <label htmlFor="college_seventh" className={styles.answer}>Seventh</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="college_eighth" name="college" value="eighth" required />
              <label htmlFor="college_eighth" className={styles.answer}>Eighth</label>
            </div>
          </div>
          <div className={styles.formGroup}>
            <label className={styles.question}>What grade are you?</label>
            <div className={styles.radioGroup}>
              <input type="radio" id="grade_first" name="grade" value="first" required />
              <label htmlFor="grade_first" className={styles.answer}>First</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="grade_second" name="grade" value="second" required />
              <label htmlFor="grade_second" className={styles.answer}>Second</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="grade_third" name="grade" value="third" required />
              <label htmlFor="grade_third" className={styles.answer}>Third</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="grade_fourth" name="grade" value="fourth" required />
              <label htmlFor="grade_fourth" className={styles.answer}>Fourth</label>
            </div>
          </div>
          <div className={styles.formGroup}>
            <label className={styles.question}>What are you studying?</label>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_humanities_social_sciences" name="major" value="humanities_social_sciences" required />
              <label htmlFor="major_humanities_social_sciences" className={styles.answer}>Humanities and Social Sciences</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_business_management" name="major" value="business_management" required />
              <label htmlFor="major_business_management" className={styles.answer}>Business and Management</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_natural_sciences_mathematics" name="major" value="natural_sciences_mathematics" required />
              <label htmlFor="major_natural_sciences_mathematics" className={styles.answer}>Natural Sciences and Mathematics</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_engineering_technology" name="major" value="engineering_technology" required />
              <label htmlFor="major_engineering_technology" className={styles.answer}>Engineering and Technology</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_health_medicine" name="major" value="health_medicine" required />
              <label htmlFor="major_health_medicine" className={styles.answer}>Health and Medicine</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_arts_design" name="major" value="arts_design" required />
              <label htmlFor="major_arts_design" className={styles.answer}>Arts and Design</label>
            </div>
            <div className={styles.radioGroup}>
              <input type="radio" id="major_other" name="major" value="other" required />
              <label htmlFor="major_other" className={styles.answer}>Other</label>
            </div>
          </div>
          <button type="submit" className={styles.submitButton}>Submit</button>
        </form>
      </div>
    </main>
  );
}
