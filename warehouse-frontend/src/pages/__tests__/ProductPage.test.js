import React from 'react';
import {render, screen, waitFor } from '@testing-library/react';
import { act } from 'react';
import ProductPage from '../ProductPage';
import GetProducts from '../../categories/Utils/GetProducts';
import ProductList from '../../categories/Product/ProductList';

describe('Product Page', () => {
    /*
    beforeEach(() => {
        jest.mock('../../categories/Utils/GetProducts');
        jest.mock('../../categories/Product/ProductList');
    })
*/
    afterEach(() => {
        jest.clearAllMocks();
    });

    test('should render the products title', async () => {
         act( () => {
            render(<ProductPage />);
        });
    
        await waitFor(() => {
            expect(screen.getByText('Products')).toBeInTheDocument();
        });
    });

    
    test('should render the sort products label', async () => {
        act( () => {
           render(<ProductPage />);
       });
   
       await waitFor(() => {
       expect(screen.getByText('Sort products by:')).toBeInTheDocument();
       });
   });
   
})