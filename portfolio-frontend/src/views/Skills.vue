<template>
  <section class="skills">
    <div class="container">
      <h2 class="section-title">Technical Skills</h2>
      <div class="skills-grid">
        <div 
          v-for="(skill, index) in skills" 
          :key="index" 
          class="skill-card"
          @mouseenter="startAnimation(index)"
        >
          <div class="skill-header">
            <i :class="skill.icon"></i>
            <h3>{{ skill.name }}</h3>
          </div>
          <div class="skill-progress">
            <div class="progress-bar">
              <div 
                class="progress-fill" 
                :style="{ width: skill.percentage + '%' }"
                :class="{ 'animate': skill.animating }"
              ></div>
            </div>
            <span class="skill-percentage">{{ skill.percentage }}%</span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
export default {
  name: 'SkillsPage',
  data() {
    return {
      skills: [
        { name: 'Vue.js', percentage: 85, icon: 'fab fa-vuejs', animating: false },
        { name: 'Angular', percentage: 80, icon: 'fab fa-angular', animating: false },
        { name: 'JavaScript', percentage: 90, icon: 'fab fa-js-square', animating: false },
        { name: 'TypeScript', percentage: 85, icon: 'fas fa-code', animating: false },
        { name: 'Java Spring Boot', percentage: 88, icon: 'fas fa-leaf', animating: false },
        { name: 'PostgreSQL', percentage: 82, icon: 'fas fa-database', animating: false },
        { name: 'MongoDB', percentage: 78, icon: 'fas fa-database', animating: false },
        { name: 'HTML/CSS', percentage: 92, icon: 'fab fa-html5', animating: false },
        { name: 'Git', percentage: 87, icon: 'fab fa-git-alt', animating: false },
        { name: 'Salesforce', percentage: 75, icon: 'fab fa-salesforce', animating: false },
        { name: 'REST APIs', percentage: 86, icon: 'fas fa-server', animating: false },
        { name: 'Docker', percentage: 70, icon: 'fab fa-docker', animating: false }
      ]
    }
  },
  methods: {
    startAnimation(index: number) {
      if (!this.skills[index].animating) {
        this.skills[index].animating = true;
        setTimeout(() => {
          this.skills[index].animating = false;
        }, 1000);
      }
    }
  }
}
</script>

<style scoped>
.skills {
  padding: 5rem 0;
  min-height: 100vh;
  background-color: #f8f9fa;
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

.skills-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 2rem;
}

.skill-card {
  background: white;
  border-radius: 10px;
  padding: 1.5rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.skill-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
}

.skill-header {
  display: flex;
  align-items: center;
  margin-bottom: 1.5rem;
}

.skill-header i {
  font-size: 2rem;
  margin-right: 1rem;
  color: #3498db;
}

.skill-header h3 {
  font-size: 1.3rem;
  color: #2c3e50;
}

.skill-progress {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.progress-bar {
  flex-grow: 1;
  height: 8px;
  background-color: #ecf0f1;
  border-radius: 4px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #3498db, #42b883);
  border-radius: 4px;
  transition: width 1s ease-in-out;
}

.progress-fill.animate {
  animation: pulse 1s ease-in-out;
}

.skill-percentage {
  font-weight: bold;
  color: #2c3e50;
  min-width: 45px;
}

@keyframes pulse {
  0% { opacity: 0.6; }
  50% { opacity: 1; }
  100% { opacity: 0.6; }
}

@media (max-width: 768px) {
  .skills-grid {
    grid-template-columns: 1fr;
  }
}
</style>