module.exports = {
    testEnvironment: 'jest-environment-jsdom',
    transform: {
      '^.+\\.jsx?$': 'babel-jest',
    },
    moduleFileExtensions: ['js', 'jsx'],
    moduleNameMapper: {
      '\\.(css|less)$': '<rootDir>/src/pages/__mocks__/styleMock.js'
    },
    globals: {
      fetch: global.fetch,
    },
    setupFilesAfterEnv: [
    "<rootDir>/src/setupTests.js"
  ]
  };