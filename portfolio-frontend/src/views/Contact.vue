<template>
  <section class="contact">
    <div class="container">
      <h2 class="section-title">Get In Touch</h2>
      <div class="contact-content">
        <div class="contact-info">
          <h3>Contact Information</h3>
          <div class="info-item">
            <i class="fas fa-envelope"></i>
            <div>
              <h4>Email</h4>
              <p>katiegomakoti@gmail.com</p>
              <p>katiegomakoti07@gmail.com</p>
            </div>
          </div>
          <div class="info-item">
            <i class="fas fa-phone"></i>
            <div>
              <h4>Phone</h4>
              <p>+27 61 419 3439</p>
              <p>+27 82 325 9473</p>
            </div>
          </div>
          <div class="info-item">
            <i class="fas fa-map-marker-alt"></i>
            <div>
              <h4>Location</h4>
              <p>Johannesburg, South Africa</p>
            </div>
          </div>
        </div>
        <form class="contact-form" @submit.prevent="submitForm">
          <div class="form-group">
            <label for="name">Name</label>
            <input 
              type="text" 
              id="name" 
              v-model="formData.name" 
              required 
            />
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input 
              type="email" 
              id="email" 
              v-model="formData.email" 
              required 
            />
          </div>
          <div class="form-group">
            <label for="message">Message</label>
            <textarea 
              id="message" 
              v-model="formData.message" 
              rows="5" 
              required
            ></textarea>
          </div>
          <button type="submit" class="submit-btn" :disabled="loading">
            {{ loading ? 'Sending...' : 'Send Message' }}
          </button>
          <div v-if="message" class="message" :class="{ 'success': isSuccess, 'error': !isSuccess }">
            {{ message }}
          </div>
        </form>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Contact',
  data() {
    return {
      formData: {
        name: '',
        email: '',
        message: ''
      },
      loading: false,
      message: '',
      isSuccess: false
    }
  },
  methods: {
    async submitForm() {
      this.loading = true;
      this.message = '';
      
      try {
        // For now, we'll simulate the API call
        // In production, replace with actual backend endpoint
        console.log('Form data:', this.formData);
        
        // Simulate API call delay
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        this.isSuccess = true;
        this.message = 'Thank you for your message! I will get back to you soon.';
        
        // Reset form
        this.formData = {
          name: '',
          email: '',
          message: ''
        };
      } catch (error) {
        this.isSuccess = false;
        this.message = 'Sorry, there was an error sending your message. Please try again.';
        console.error('Error:', error);
      } finally {
        this.loading = false;
      }
    }
  }
}
</script>

<style scoped>
.contact {
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

.contact-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 3rem;
}

.contact-info {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.contact-info h3 {
  font-size: 1.5rem;
  margin-bottom: 2rem;
  color: #2c3e50;
}

.info-item {
  display: flex;
  align-items: flex-start;
  margin-bottom: 1.5rem;
}

.info-item i {
  font-size: 1.5rem;
  color: #3498db;
  margin-right: 1rem;
  margin-top: 0.25rem;
}

.info-item h4 {
  font-size: 1.1rem;
  margin-bottom: 0.5rem;
  color: #2c3e50;
}

.info-item p {
  color: #7f8c8d;
  margin-bottom: 0.25rem;
}

.contact-form {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 600;
  color: #2c3e50;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  transition: border-color 0.3s ease;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #3498db;
}

.submit-btn {
  background: linear-gradient(135deg, #3498db, #42b883);
  color: white;
  border: none;
  padding: 0.75rem 2rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.submit-btn:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(52, 152, 219, 0.3);
}

.submit-btn:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.message {
  margin-top: 1rem;
  padding: 1rem;
  border-radius: 5px;
  font-weight: 600;
}

.message.success {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.message.error {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

@media (max-width: 968px) {
  .contact-content {
    grid-template-columns: 1fr;
    gap: 2rem;
  }
}

@media (max-width: 576px) {
  .section-title {
    font-size: 2rem;
  }
  
  .info-item {
    flex-direction: column;
  }
  
  .info-item i {
    margin-bottom: 0.5rem;
  }
}
</style>