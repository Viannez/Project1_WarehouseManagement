import React from 'react';
import {render, screen } from '@testing-library/react';
import { act } from 'react';
import ProductDetails from '../ProductDetails';

describe('Product Details', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    // tests if the Product title appears on the Product Detail page
    test('should render the product title', () => {
         act( () => {
            render(<ProductDetails />);
        });
    
        expect(screen.getByText('Product')).toBeInTheDocument();
    });

    // tests if the Product Detail page has the details of the product displayed
    test('should render the product details', () => {
        act( () => {
           render(<ProductDetails />);
       });
   
       expect(screen.getByText('Name:')).toBeInTheDocument();
       expect(screen.getByText('Size:')).toBeInTheDocument();
       expect(screen.getByText('Price:')).toBeInTheDocument();
       expect(screen.getByText('This product is located in the following warehouses:')).toBeInTheDocument();
   });
})