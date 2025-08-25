<template>
  <section class="about">
    <div class="container">
      <h2 class="section-title">About Me</h2>
      <div class="about-content">
        <div class="profile-card">
          <div class="profile-image">
            <img src="@/assets/profile.jpg" alt="Katlego Makoti" />
          </div>
          <div class="profile-info">
            <h3>Katlego Makoti</h3>
            <p>Full-Stack Developer</p>
            <p>Johannesburg, South Africa</p>
          </div>
        </div>
        <div class="about-text">
          <p>
            I am a passionate full-stack developer with strong proficiency in Angular, Spring Boot, and PostgreSQL, 
            complemented by hands-on experience in Salesforce development and administration. I build scalable, 
            user-focused web applications with clean architecture and well-structured backend services.
          </p>
          <p>
            My experience includes integrating third-party APIs, performance optimization, and delivering responsive 
            user interfaces. I'm committed to continuous learning and staying up to date with evolving web and 
            Salesforce technologies to deliver effective, modern solutions.
          </p>
          <p>
            I thrive in collaborative teams and enjoy sharing knowledge and contributing to impactful projects.
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'About'
}
</script>

<style scoped>
.about {
  padding: 5rem 0;
  min-height: 100vh;
  display: flex;
  align-items: center;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 2rem;
}

.section-title {
  text-align: center;
  font-size: 2.5rem;
  margin-bottom: 3rem;
  color: #2c3e50;
}

.about-content {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 3rem;
  align-items: start;
}

.profile-card {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.profile-image {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  overflow: hidden;
  margin: 0 auto 1.5rem;
  border: 5px solid #f8f9fa;
}

.profile-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-info h3 {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
  color: #2c3e50;
}

.profile-info p {
  color: #7f8c8d;
  margin-bottom: 0.5rem;
}

.about-text {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.about-text p {
  margin-bottom: 1.5rem;
  line-height: 1.8;
  color: #34495e;
}

@media (max-width: 968px) {
  .about-content {
    grid-template-columns: 1fr;
    gap: 2rem;
  }
  
  .profile-card {
    max-width: 400px;
    margin: 0 auto;
  }
}

@media (max-width: 576px) {
  .section-title {
    font-size: 2rem;
  }
  
  .profile-image {
    width: 150px;
    height: 150px;
  }
}
</style>