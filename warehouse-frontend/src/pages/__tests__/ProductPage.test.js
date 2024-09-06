import React from 'react';
import {render, screen, waitFor } from '@testing-library/react';
import { act } from 'react';
import ProductPage from '../ProductPage';

describe('Product Page', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    // tests if the Products title appears on the Products page
    test('should render the products title', async () => {
         act( () => {
            render(<ProductPage />);
        });
    
        await waitFor(() => {
            expect(screen.getByText('Products')).toBeInTheDocument();
        });
    });

    // tests if the Products page has the sorting label displayed
    test('should render the sort products label', async () => {
        act( () => {
           render(<ProductPage />);
       });
   
       await waitFor(() => {
       expect(screen.getByText('Sort products by:')).toBeInTheDocument();
       });
   });
   
})