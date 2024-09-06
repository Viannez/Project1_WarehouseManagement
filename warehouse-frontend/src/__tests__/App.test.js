import React from 'react';
import {render, screen} from '@testing-library/react';
import App from '../App';

const renderApp = () => {
    return render(<App />);
}

describe('App component', () => {
    test('renders the Mystery Box Warehouses title', () => {
        renderApp();
        expect(screen.getByText('Mystery Box Warehouses')).toBeInTheDocument();
    });
});