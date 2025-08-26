<!-- eslint-disable @typescript-eslint/no-require-imports -->
<template>
  <section class="skills">
    <div class="container">
      <div class="section-header">
        <h2 class="section-title">Technical Expertise</h2>
        <p class="section-subtitle">
          Comprehensive technical skills developed through real-world projects and professional
          training, with hands-on experience in full-stack development and Salesforce solutions
        </p>
      </div>

      <!-- Filter Tabs -->
      <div class="filter-tabs">
        <button
          v-for="category in categories"
          :key="category"
          :class="['filter-tab', { active: activeCategory === category }]"
          @click="setActiveCategory(category)"
        >
          <i :class="getCategoryIcon(category)"></i>
          {{ category }}
        </button>
      </div>

      <!-- Skills Grid -->
      <div class="skills-grid">
        <div
          v-for="(skill, index) in filteredSkills"
          :key="skill.id"
          class="skill-card"
          :class="{ visible: skill.visible }"
          @mouseenter="handleMouseEnter(skill)"
          @mouseleave="handleMouseLeave(skill)"
          :style="{ animationDelay: `${index * 100}ms` }"
        >
          <div class="skill-header">
            <div class="icon-container">
              <i :class="skill.icon" :style="{ color: skill.color }"></i>
              <div class="proficiency-badge" :class="getSkillLevel(skill.percentage)">
                {{ getSkillLevelText(skill.percentage) }}
              </div>
            </div>
            <div class="skill-info">
              <h3>{{ skill.name }}</h3>
              <span class="skill-category">{{ skill.category }}</span>
            </div>
          </div>

          <div class="skill-progress">
            <div class="progress-container">
              <div class="progress-bar">
                <div
                  class="progress-fill"
                  :style="{
                    width: skill.animating ? skill.percentage + '%' : '0%',
                    background: skill.gradient,
                  }"
                ></div>
                <div class="progress-glow" v-if="skill.animating"></div>
              </div>
              <span class="skill-percentage">{{ skill.percentage }}%</span>
            </div>
            <div class="skill-description">
              {{ skill.description }}
            </div>
          </div>

          <!-- Experience Years -->
          <div class="experience-badge">
            <i class="fas fa-calendar-alt"></i>
            <span>{{ skill.experience }} experience</span>
          </div>

          <!-- Project Tags -->
          <div class="project-tags" v-if="skill.projects.length > 0">
            <h4>Used in Projects:</h4>
            <div class="tags-container">
              <span v-for="project in skill.projects" :key="project" class="project-tag">
                {{ project }}
              </span>
            </div>
          </div>
        </div>
      </div>

      <div v-if="activeCategory === 'Salesforce'" class="trailblazer-section">
        <div class="trailblazer-card">
          <div class="trailblazer-header">
            <div class="trailblazer-avatar">
              <i class="fab fa-salesforce"></i>
            </div>
            <div class="trailblazer-info">
              <h3>Salesforce Trailblazer Profile</h3>
              <p>Certified Salesforce Developer with hands-on platform experience</p>
            </div>
            <div class="trailblazer-badges-count">
              <span class="badge-count">{{ trailblazerData.totalBadges }}</span>
              <span class="badge-label">Badges Earned</span>
            </div>
          </div>

          <div class="trailblazer-stats">
            <div class="stat-item">
              <i class="fas fa-medal"></i>
              <span class="stat-number">{{ trailblazerData.totalPoints }}</span>
              <span class="stat-label">Total Points</span>
            </div>
            <div class="stat-item">
              <i class="fas fa-hiking"></i>
              <span class="stat-number">{{ trailblazerData.completedTrails }}</span>
              <span class="stat-label">Trails</span>
            </div>
            <div class="stat-item">
              <i class="fas fa-certificate"></i>
              <span class="stat-number">{{ trailblazerData.certifications }}</span>
              <span class="stat-label">Certifications</span>
            </div>
            <div class="stat-item">
              <i class="fas fa-calendar-check"></i>
              <span class="stat-number">{{ trailblazerData.streakDays }}</span>
              <span class="stat-label">Super Badges</span>
            </div>
          </div>

          <div class="recent-badges">
            <h4>Recent Badges</h4>
            <div class="badges-grid">
              <div
                v-for="badge in trailblazerData.recentBadges"
                :key="badge.id"
                class="badge-item"
                :class="{ superbadge: badge.isSuperbadge }"
              >
                <div class="badge-icon">
                  <img :src="badge.imageKey" :alt="badge.name" class="badge-image" />
                </div>
                <div class="badge-details">
                  <h5>{{ badge.name }}</h5>
                  <span class="badge-type">{{ badge.type }}</span>
                  <span class="badge-date">{{ badge.earnedDate }}</span>
                </div>
              </div>
            </div>
          </div>

          <div class="trailblazer-actions">
            <a :href="trailblazerProfileUrl" target="_blank" class="profile-link">
              <i class="fas fa-external-link-alt"></i>
              View Full Trailblazer Profile
            </a>
          </div>
        </div>
      </div>

      <!-- Professional Summary -->
      <div class="professional-summary">
        <div class="summary-card">
          <h3>Professional Impact</h3>
          <div class="impact-stats">
            <div class="stat">
              <span class="stat-number">{{ totalSkills }}</span>
              <span class="stat-label">Technical Skills</span>
            </div>
            <div class="stat">
              <span class="stat-number">{{ completedProjects }}</span>
              <span class="stat-label">Completed Projects</span>
            </div>
            <div class="stat">
              <span class="stat-number">{{ averageExperience }}+</span>
              <span class="stat-label">Years Experience</span>
            </div>
            <div class="stat">
              <span class="stat-number">{{ certifications }}</span>
              <span class="stat-label">Certifications</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
interface Skill {
  id: number
  name: string
  percentage: number
  icon: string
  color: string
  gradient: string
  category: string
  description: string
  experience: string
  projects: string[]
  animating: boolean
  visible: boolean
}

// // Add these imports at the top of your script section
// import largeLanguageModels from '/@portfolio-frontend/public/salesforce-images/commandLineInterface.webp'
// import quickStartVSCode from '@/salesforce-images/quickStartVSCodeforDevelopment.webp'
// import setupLightning from '@/salesforce-images/setupLightningWebComponents.webp'
// import commandLineInterface from '@/salesforce-images/commandLineInterface.webp'
// //import defaultImage from '@/public/salesforce-images/default.webp' // fallback image

export default {
  name: 'EnhancedSkillsShowcase',
  data() {
    return {
      trailblazerProfileUrl: 'https://www.salesforce.com/trailblazer/w9t9w0rbid8513yqko',
      trailblazerData: {
        totalBadges: 100,
        totalPoints: 89575,
        completedTrails: 12,
        certifications: 1,
        streakDays: 15, //superbadge
        recentBadges: [
          {
            id: 1,
            name: 'Large Language Models',
            type: 'Module',
            earnedDate: 'Dec 2024',
            imageKey: '/public/salesforce-images/commandLineInterface.webp',
            color: 'linear-gradient(135deg, #00a1e0, #0082b3)',
            isSuperbadge: false,
          },
          {
            id: 2,
            name: 'Set Up Your Lightning Web Components Developer Tools',
            type: 'Module',
            earnedDate: 'Dec 2024',
            imageKey: '/salesforce-images/quickStartVSCodeforDevelopment.webp',
            color: 'linear-gradient(135deg, #ff6b6b, #ee5a5a)',
            isSuperbadge: false,
          },
          {
            id: 3,
            name: 'Quick Start: Visual Studio Code for Salesforce Development',
            type: 'Superbadge',
            earnedDate: 'Nov 2024',
            imageKey:'/salesforce-images/quickStartVSCodeforDevelopment.webp',
            color: 'linear-gradient(135deg, #ffd700, #ffcd00)',
            isSuperbadge: true,
          },
          {
            id: 4,
            name: 'Command-Line Interface',
            type: 'Trail',
            earnedDate: 'Nov 2024',
            imageKey: '/salesforce-images/commandLineInterface.webp',
            color: 'linear-gradient(135deg, #4ecdc4, #44b3a9)',
            isSuperbadge: false,
          },
        ],
      },
      activeCategory: 'All',
      skills: [
        {
          id: 1,
          name: 'Angular',
          percentage: 88,
          icon: 'fab fa-angular',
          color: '#dd0031',
          gradient: 'linear-gradient(135deg, #dd0031, #c3002f)',
          category: 'Frontend',
          description:
            'Advanced framework experience with component architecture, services, and routing',
          experience: '1+ years',
          projects: ['Mzansi Plaza', 'iRenew Store', 'FootyFocus'],
          animating: false,
          visible: true,
        },
        {
          id: 2,
          name: 'Java Spring Boot',
          percentage: 85,
          icon: 'fas fa-leaf',
          color: '#6db33f',
          gradient: 'linear-gradient(135deg, #6db33f, #5a9934)',
          category: 'Backend',
          description:
            'RESTful API development, microservices architecture, and database integration',
          experience: '1+ years',
          projects: ['Mzansi Plaza', 'iRenew Store', 'FootyFocus', 'Tech-Oracle'],
          animating: false,
          visible: true,
        },
        {
          id: 3,
          name: 'PostgreSQL',
          percentage: 82,
          icon: 'fas fa-database',
          color: '#336791',
          gradient: 'linear-gradient(135deg, #336791, #2a5575)',
          category: 'Database',
          description: 'Database design, optimization, and complex query development',
          experience: '1+ years',
          projects: ['Mzansi Plaza', 'iRenew Store', 'FootyFocus'],
          animating: false,
          visible: true,
        },
        {
          id: 4,
          name: 'TypeScript',
          percentage: 87,
          icon: 'fas fa-code',
          color: '#3178c6',
          gradient: 'linear-gradient(135deg, #3178c6, #2761a3)',
          category: 'Frontend',
          description: 'Type-safe JavaScript development with advanced typing and interfaces',
          experience: '1+ years',
          projects: ['All Recent Projects'],
          animating: false,
          visible: true,
        },
        {
          id: 5,
          name: 'JavaScript',
          percentage: 90,
          icon: 'fab fa-js-square',
          color: '#f7df1e',
          gradient: 'linear-gradient(135deg, #f7df1e, #d4bd18)',
          category: 'Frontend',
          description: 'ES6+ features, async programming, DOM manipulation, and modern frameworks',
          experience: '2+ years',
          projects: ['All Projects'],
          animating: false,
          visible: true,
        },
        {
          id: 6,
          name: 'Salesforce (Apex & LWC)',
          percentage: 78,
          icon: 'fab fa-salesforce',
          color: '#00a1e0',
          gradient: 'linear-gradient(135deg, #00a1e0, #0082b3)',
          category: 'Salesforce',
          description:
            'Lightning Web Components, Apex triggers, Flow Builder, and platform automation',
          experience: '8 months',
          projects: ['ABSA Salesforce Projects'],
          animating: false,
          visible: true,
        },
        {
          id: 7,
          name: 'HTML5 & CSS3',
          percentage: 92,
          icon: 'fab fa-html5',
          color: '#e34f26',
          gradient: 'linear-gradient(135deg, #e34f26, #c73e1d)',
          category: 'Frontend',
          description:
            'Semantic HTML, responsive design, CSS Grid, Flexbox, and SCSS preprocessing',
          experience: '2+ years',
          projects: ['All Frontend Projects'],
          animating: false,
          visible: true,
        },
        {
          id: 8,
          name: 'Git & GitHub',
          percentage: 85,
          icon: 'fab fa-git-alt',
          color: '#f05032',
          gradient: 'linear-gradient(135deg, #f05032, #d43d26)',
          category: 'DevOps',
          description: 'Version control, branching strategies, collaborative development workflows',
          experience: '2+ years',
          projects: ['All Team Projects'],
          animating: false,
          visible: true,
        },
        {
          id: 9,
          name: 'REST APIs',
          percentage: 90,
          icon: 'fas fa-server',
          color: '#61dafb',
          gradient: 'linear-gradient(135deg, #61dafb, #4fa8c5)',
          category: 'Backend',
          description:
            'API design, integration, authentication, and third-party service consumption',
          experience: '2+ years',
          projects: ['All Backend Projects'],
          animating: false,
          visible: true,
        },
        {
          id: 10,
          name: 'Vue.js',
          percentage: 55,
          icon: 'fab fa-vuejs',
          color: '#4fc08d',
          gradient: 'linear-gradient(135deg, #4fc08d, #42a070)',
          category: 'Frontend',
          description: 'Component-based development, state management, and reactive programming',
          experience: '6 months',
          projects: ['Personal Learning Projects', 'Portfolio Site'],
          animating: false,
          visible: true,
        },
        {
          id: 11,
          name: 'MongoDB',
          percentage: 72,
          icon: 'fas fa-leaf',
          color: '#47a248',
          gradient: 'linear-gradient(135deg, #47a248, #3a8539)',
          category: 'Database',
          description: 'NoSQL database design, aggregation pipelines, and document modeling',
          experience: '8 months',
          projects: ['Learning & Practice Projects', 'Tech-Oracle'],
          animating: false,
          visible: true,
        },
        {
          id: 12,
          name: 'React',
          percentage: 70,
          icon: 'fab fa-react',
          color: '#61dafb',
          gradient: 'linear-gradient(135deg, #61dafb, #4fa8c5)',
          category: 'Frontend',
          description: 'Component lifecycle, hooks, state management, and modern React patterns',
          experience: '6 months',
          projects: ['Learning Projects', 'Tech-Oracle'],
          animating: false,
          visible: true,
        },
      ] as Skill[],
      categories: ['All', 'Frontend', 'Backend', 'Database', 'Salesforce', 'DevOps'],
      completedProjects: 4,
      certifications: 9,
    }
  },
  computed: {
    filteredSkills() {
      return this.activeCategory === 'All'
        ? this.skills
        : this.skills.filter((skill) => skill.category === this.activeCategory)
    },
    totalSkills() {
      return this.skills.length
    },
    averageExperience() {
      const total = this.skills.reduce((sum, skill) => {
        const years = parseFloat(skill.experience.replace(/[^\d.]/g, '')) || 0
        return sum + years
      }, 0)
      return Math.round(total / this.skills.length)
    },
  },
  methods: {
    setActiveCategory(category: string) {
      this.activeCategory = category
      this.animateFilteredSkills()
    },
    animateFilteredSkills() {
      this.filteredSkills.forEach((skill, index) => {
        setTimeout(() => {
          skill.visible = true
          skill.animating = true
        }, index * 100)
      })
    },
    handleMouseEnter(skill: Skill) {
      if (!skill.animating) {
        skill.animating = true
      }
    },
    handleMouseLeave(skill: Skill) {
      // Keep animation running for visual appeal
    },
    getSkillLevel(percentage: number) {
      if (percentage >= 85) return 'expert'
      if (percentage >= 75) return 'advanced'
      if (percentage >= 65) return 'intermediate'
      return 'beginner'
    },
    getSkillLevelText(percentage: number) {
      if (percentage >= 85) return 'Expert'
      if (percentage >= 75) return 'Advanced'
      if (percentage >= 65) return 'Intermediate'
      return 'Beginner'
    },
    getCategoryIcon(category: string) {
      const icons: { [key: string]: string } = {
        All: 'fas fa-th-large',
        Frontend: 'fas fa-desktop',
        Backend: 'fas fa-server',
        Database: 'fas fa-database',
        Salesforce: 'fab fa-salesforce',
        DevOps: 'fas fa-cogs',
      }
      return icons[category] || 'fas fa-code'
    },

    skillImages(key: string): string | null {
      const imageMap: Record<string, string> = {
        'largeLanguageModels': '/salesforce-images/largeLanguageModels.webp',
        'quickStartVSCode': '/salesforce-images/quickStartVSCodeforDevelopment.webp',
        'setupLightning': '/salesforce-images/setupLightningWebComponents.webp',
        'commandLineInterface': '/salesforce-images/commandLineInterface.webp',
      }
      return imageMap[key] || null
    },
  },
  mounted() {
    // Animate skills on page load
    setTimeout(() => {
      this.animateFilteredSkills()
    }, 500)
  },
}
</script>

<style scoped>

.badge-image{
  width: 4rem;
}
.skills {
  padding: 5rem 0;
  min-height: 100vh;
  background-color: #f8f9fa;
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 2rem;
  position: relative;
  z-index: 1;
}

.section-header {
  text-align: center;
  margin-bottom: 4rem;
}

.section-title {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  color: #2c3e50;
  font-weight: 700;
}

.section-subtitle {
  font-size: 1.1rem;
  color: #7f8c8d;
  max-width: 800px;
  margin: 0 auto;
  line-height: 1.6;
}

.filter-tabs {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 1rem;
  margin-bottom: 3rem;
}

.filter-tab {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.8rem 1.5rem;
  background: white;
  border: 2px solid #ecf0f1;
  border-radius: 50px;
  color: #2c3e50;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
}

.filter-tab:hover {
  background: #3498db;
  color: white;
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(52, 152, 219, 0.3);
  border-color: #3498db;
}

.filter-tab.active {
  background: #3498db;
  color: white;
  border-color: #3498db;
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(52, 152, 219, 0.3);
}

.skills-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 2rem;
  margin-bottom: 4rem;
}

.skill-card {
  background: white;
  border-radius: 20px;
  padding: 2rem;
  border: 1px solid rgba(0, 0, 0, 0.1);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
  transform: translateY(20px);
  opacity: 0;
  animation: slideInUp 0.6s ease forwards;
}

.skill-card.visible {
  transform: translateY(0);
  opacity: 1;
}

.skill-card:hover {
  transform: translateY(-10px) scale(1.02);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
}

.skill-header {
  display: flex;
  align-items: flex-start;
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.icon-container {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

.icon-container i {
  font-size: 2.5rem;
  padding: 1rem;
  background: rgba(52, 152, 219, 0.1);
  border-radius: 15px;
  transition: all 0.3s ease;
}

.skill-card:hover .icon-container i {
  transform: scale(1.1) rotate(5deg);
}

.proficiency-badge {
  padding: 0.3rem 0.8rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.proficiency-badge.expert {
  background: linear-gradient(45deg, #4caf50, #45a049);
  color: white;
}

.proficiency-badge.advanced {
  background: linear-gradient(45deg, #2196f3, #1976d2);
  color: white;
}

.proficiency-badge.intermediate {
  background: linear-gradient(45deg, #ff9800, #f57000);
  color: white;
}

.proficiency-badge.beginner {
  background: linear-gradient(45deg, #9c27b0, #7b1fa2);
  color: white;
}

.skill-info h3 {
  font-size: 1.4rem;
  color: #2c3e50;
  margin-bottom: 0.5rem;
  font-weight: 700;
}

.skill-category {
  color: #7f8c8d;
  font-size: 0.9rem;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.progress-container {
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1rem;
}

.progress-bar {
  flex-grow: 1;
  height: 12px;
  background: rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
  position: relative;
}

.progress-fill {
  height: 100%;
  background: linear-gradient(90deg, #3498db, #42b883);
  border-radius: 10px;
  transition: width 2s cubic-bezier(0.4, 0, 0.2, 1);
  position: relative;
  overflow: hidden;
}

.progress-fill::after {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.4), transparent);
  animation: shimmer 2s infinite;
}

.progress-glow {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 10px;
  background: inherit;
  filter: blur(4px);
  opacity: 0.6;
  z-index: -1;
}

.skill-percentage {
  font-weight: 700;
  color: #2c3e50;
  font-size: 1.1rem;
  min-width: 50px;
  text-align: right;
}

.skill-description {
  color: #5a6c7d;
  font-size: 0.95rem;
  line-height: 1.5;
  margin-bottom: 1.5rem;
}

.experience-badge {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  background: linear-gradient(45deg, #f8f9fa, #e9ecef);
  padding: 0.6rem 1rem;
  border-radius: 25px;
  color: #495057;
  font-size: 0.85rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

.project-tags h4 {
  color: #2c3e50;
  font-size: 0.9rem;
  margin-bottom: 0.8rem;
  font-weight: 600;
}

.tags-container {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.project-tag {
  background: linear-gradient(90deg, #3498db, #42b883);
  color: white;
  padding: 0.4rem 0.8rem;
  border-radius: 15px;
  font-size: 0.75rem;
  font-weight: 500;
  transition: all 0.3s ease;
}

.project-tag:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 12px rgba(52, 152, 219, 0.3);
}

.professional-summary {
  margin-top: 3rem;
}

.summary-card {
  background: white;
  border-radius: 20px;
  padding: 2.5rem;
  border: 1px solid rgba(0, 0, 0, 0.1);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.summary-card h3 {
  font-size: 2rem;
  color: #2c3e50;
  margin-bottom: 2rem;
  font-weight: 700;
}

.impact-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 2rem;
}

.stat {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.stat-number {
  font-size: 2.5rem;
  font-weight: 700;
  color: #3498db;
  margin-bottom: 0.5rem;
}

.stat-label {
  color: #5a6c7d;
  font-size: 0.9rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.trailblazer-section {
  margin-top: 2rem;
  animation: slideInUp 0.6s ease forwards;
}

.trailblazer-card {
  background: white;
  border-radius: 20px;
  padding: 2.5rem;
  border: 1px solid rgba(0, 0, 0, 0.1);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.trailblazer-header {
  display: flex;
  align-items: center;
  gap: 2rem;
  margin-bottom: 2rem;
  padding-bottom: 2rem;
  border-bottom: 2px solid #ecf0f1;
}

.trailblazer-avatar {
  width: 80px;
  height: 80px;
  background: linear-gradient(135deg, #00a1e0, #0082b3);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 8px 25px rgba(0, 161, 224, 0.3);
}

.trailblazer-avatar i {
  font-size: 2.5rem;
  color: white;
}

.trailblazer-info {
  flex-grow: 1;
}

.trailblazer-info h3 {
  font-size: 1.8rem;
  color: #2c3e50;
  margin-bottom: 0.5rem;
  font-weight: 700;
}

.trailblazer-info p {
  color: #7f8c8d;
  font-size: 1.1rem;
}

.trailblazer-badges-count {
  text-align: center;
  padding: 1rem 1.5rem;
  background: linear-gradient(135deg, #f8f9fa, #e9ecef);
  border-radius: 15px;
  border: 2px solid #ecf0f1;
}

.badge-count {
  display: block;
  font-size: 2rem;
  font-weight: 700;
  color: #00a1e0;
  margin-bottom: 0.3rem;
}

.badge-label {
  font-size: 0.85rem;
  color: #5a6c7d;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.trailblazer-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2.5rem;
  padding: 2rem;
  background: linear-gradient(135deg, #f8f9fa, #e9ecef);
  border-radius: 15px;
}

.stat-item {
  text-align: center;
  padding: 1rem;
  background: white;
  border-radius: 12px;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
}

.stat-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.12);
}

.stat-item i {
  font-size: 1.8rem;
  color: #00a1e0;
  margin-bottom: 0.8rem;
}

.stat-number {
  display: block;
  font-size: 1.6rem;
  font-weight: 700;
  color: #2c3e50;
  margin-bottom: 0.3rem;
}

.stat-label {
  font-size: 0.8rem;
  color: #7f8c8d;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.recent-badges h4 {
  font-size: 1.4rem;
  color: #2c3e50;
  margin-bottom: 1.5rem;
  font-weight: 700;
}

.badges-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.badge-item {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1.2rem;
  background: white;
  border: 2px solid #ecf0f1;
  border-radius: 15px;
  transition: all 0.3s ease;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.08);
}

.badge-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.12);
  border-color: #00a1e0;
}

.badge-item.superbadge {
  border-color: #ffd700;
  background: linear-gradient(135deg, #fffacd, #fff8dc);
}

.badge-item.superbadge:hover {
  border-color: #ffd700;
  box-shadow: 0 8px 25px rgba(255, 215, 0, 0.3);
}

.badge-icon {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.badge-icon i {
  font-size: 1.3rem;
  color: white;
}

.badge-details {
  flex-grow: 1;
}

.badge-details h5 {
  font-size: 1.1rem;
  color: #2c3e50;
  margin-bottom: 0.3rem;
  font-weight: 600;
}

.badge-type {
  display: inline-block;
  background: #00a1e0;
  color: white;
  padding: 0.2rem 0.6rem;
  border-radius: 10px;
  font-size: 0.7rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin-right: 0.5rem;
}

.badge-item.superbadge .badge-type {
  background: #ffd700;
  color: #2c3e50;
}

.badge-date {
  color: #7f8c8d;
  font-size: 0.85rem;
  font-weight: 500;
}

.trailblazer-actions {
  text-align: center;
  padding-top: 2rem;
  border-top: 2px solid #ecf0f1;
}

.profile-link {
  display: inline-flex;
  align-items: center;
  gap: 0.8rem;
  padding: 1rem 2rem;
  background: linear-gradient(135deg, #00a1e0, #0082b3);
  color: white;
  text-decoration: none;
  border-radius: 50px;
  font-weight: 600;
  font-size: 1.1rem;
  transition: all 0.3s ease;
  box-shadow: 0 5px 15px rgba(0, 161, 224, 0.3);
}

.profile-link:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(0, 161, 224, 0.4);
  text-decoration: none;
  color: white;
}

@keyframes slideInUp {
  from {
    transform: translateY(30px);
    opacity: 0;
  }

  to {
    transform: translateY(0);
    opacity: 1;
  }
}

@keyframes shimmer {
  0% {
    left: -100%;
  }

  100% {
    left: 100%;
  }
}

@media (max-width: 768px) {
  .trailblazer-header {
    flex-direction: column;
    text-align: center;
    gap: 1.5rem;
  }

  .trailblazer-stats {
    grid-template-columns: repeat(2, 1fr);
  }

  .badges-grid {
    grid-template-columns: 1fr;
  }

  .section-title {
    font-size: 2.2rem;
  }

  .skills-grid {
    grid-template-columns: 1fr;
  }

  .filter-tabs {
    gap: 0.5rem;
  }

  .filter-tab {
    padding: 0.6rem 1rem;
    font-size: 0.9rem;
  }

  .skill-card {
    padding: 1.5rem;
  }
}

@media (max-width: 480px) {
  .trailblazer-card {
    padding: 1.5rem;
  }

  .trailblazer-stats {
    grid-template-columns: 1fr;
    gap: 1rem;
  }

  .container {
    padding: 0 1rem;
  }

  .section-title {
    font-size: 1.8rem;
  }

  .section-subtitle {
    font-size: 1rem;
  }

  .impact-stats {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>
