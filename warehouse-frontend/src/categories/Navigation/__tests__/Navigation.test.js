import React from 'react';
import {render, screen, waitFor} from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import { act } from 'react';
import Navigation from '../Navigation';

describe('Navigation bar', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    // tests if the nav bar displays the Warehouses and Products options
    test('should render the warehouses and products nav options', async () => {
        await act(async () => {
            render(
                <BrowserRouter>
                    <Navigation />
                </BrowserRouter>
            );
        });
    
        await waitFor(() => {
            expect(screen.getByText('Warehouses')).toBeInTheDocument();
            expect(screen.getByText('Products')).toBeInTheDocument();
        });
    });
})