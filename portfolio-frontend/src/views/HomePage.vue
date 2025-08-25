<template>
  <section class="hero">
    <div class="hero-content">
      <h1 class="hero-title">Katlego Makoti</h1>
      <h2 class="hero-subtitle">Full-Stack Developer</h2>
      <div class="social-links">
        <a href="https://linkedin.com/in/katlego-makoti" target="_blank" class="social-link">
          <i class="fab fa-linkedin"></i>
        </a>
        <a href="https://github.com/katlegomakoti" target="_blank" class="social-link">
          <i class="fab fa-github"></i>
        </a>
      </div>
    </div>
    <div class="scroll-indicator">
      <span>Scroll down</span>
      <i class="fas fa-chevron-down"></i>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style scoped>
.hero {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  background: linear-gradient(135deg, #2c3e50 0%, #3498db 100%);
  color: white;
  position: relative;
}

.hero-content {
  z-index: 1;
}

.hero-title {
  font-size: 4rem;
  margin-bottom: 1rem;
  animation: fadeInDown 1s ease;
}

.hero-subtitle {
  font-size: 2rem;
  margin-bottom: 2rem;
  opacity: 0.9;
  animation: fadeInUp 1s ease 0.5s both;
}

.social-links {
  display: flex;
  justify-content: center;
  gap: 1.5rem;
  animation: fadeIn 1s ease 1s both;
}

.social-link {
  color: white;
  font-size: 2rem;
  transition: transform 0.3s ease, color 0.3s ease;
}

.social-link:hover {
  transform: translateY(-5px);
  color: #42b883;
}

.scroll-indicator {
  position: absolute;
  bottom: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
  animation: bounce 2s infinite;
  color: white;
}

.scroll-indicator i {
  margin-top: 0.5rem;
}

@keyframes fadeInDown {
  from {
    opacity: 0;
    transform: translateY(-30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(30px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

@keyframes bounce {
  0%, 20%, 50%, 80%, 100% {
    transform: translateY(0);
  }
  40% {
    transform: translateY(-10px);
  }
  60% {
    transform: translateY(-5px);
  }
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 2.5rem;
  }
  
  .hero-subtitle {
    font-size: 1.5rem;
  }
  
  .social-link {
    font-size: 1.5rem;
  }
}
</style>