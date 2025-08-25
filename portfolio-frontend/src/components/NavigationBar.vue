<template>
  <nav class="navbar">
    <div class="nav-container">
      <div class="nav-logo">
        <router-link to="/">Katlego Makoti</router-link>
      </div>
      <ul class="nav-menu">
        <li class="nav-item">
          <router-link to="/" class="nav-link">Home</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/about" class="nav-link">About</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/skills" class="nav-link">Skills</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/contact" class="nav-link">Contact</router-link>
        </li>
      </ul>
      <div class="hamburger">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'NavigationBar',
  mounted() {
    // Mobile menu toggle functionality
    const hamburger = document.querySelector(".hamburger");
    const navMenu = document.querySelector(".nav-menu");

    hamburger.addEventListener("click", () => {
      hamburger.classList.toggle("active");
      navMenu.classList.toggle("active");
    });

    document.querySelectorAll(".nav-link").forEach(n => n.addEventListener("click", () => {
      hamburger.classList.remove("active");
      navMenu.classList.remove("active");
    }));
  }
}
</script>

<style scoped>
.navbar {
  background-color: #2c3e50;
  height: 70px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.2rem;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.nav-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 70px;
  max-width: 1300px;
  margin: 0 auto;
  padding: 0 50px;
}

.nav-logo {
  color: #fff;
  cursor: pointer;
  text-decoration: none;
  font-size: 2rem;
  font-weight: bold;
}

.nav-logo a {
  color: #fff;
  text-decoration: none;
}

.nav-menu {
  display: flex;
  list-style: none;
  text-align: center;
}

.nav-item {
  line-height: 40px;
  margin-right: 1rem;
}

.nav-link {
  color: #fff;
  text-decoration: none;
  padding: 0.5rem 1rem;
  height: 100%;
  border-bottom: 3px solid transparent;
  transition: all 0.3s ease;
}

.nav-link:hover {
  border-bottom: 3px solid #42b883;
}

.hamburger {
  display: none;
  color: #fff;
}

@media screen and (max-width: 960px) {
  .nav-menu {
    display: flex;
    flex-direction: column;
    width: 100%;
    border-top: 1px solid #fff;
    position: absolute;
    top: 70px;
    left: -110%;
    opacity: 1;
    transition: all 0.5s ease;
  }

  .nav-menu.active {
    background: #2c3e50;
    left: 0;
    opacity: 1;
    transition: all 0.5s ease;
    z-index: 1;
  }

  .nav-item {
    width: 100%;
    margin: 0;
  }

  .nav-link {
    padding: 1.5rem;
    width: 100%;
    display: table;
  }

  .hamburger {
    display: block;
    cursor: pointer;
  }

  .bar {
    display: block;
    width: 25px;
    height: 3px;
    margin: 5px auto;
    transition: all 0.3s ease-in-out;
    background-color: #fff;
  }

  .hamburger.active .bar:nth-child(2) {
    opacity: 0;
  }

  .hamburger.active .bar:nth-child(1) {
    transform: translateY(8px) rotate(45deg);
  }

  .hamburger.active .bar:nth-child(3) {
    transform: translateY(-8px) rotate(-45deg);
  }
}
</style>