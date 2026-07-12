// vitest.config.js
import { defineConfig } from 'vitest/config'

export default defineConfig({
  test: {
    // src/test/javascript 内にある .test.js または .spec.js を対象にする
    include: ['src/test/javascript/**/*.{test,spec}.js'],
  },
})