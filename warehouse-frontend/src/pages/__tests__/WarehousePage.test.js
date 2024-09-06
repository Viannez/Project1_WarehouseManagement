import React from 'react';
import {render, screen } from '@testing-library/react';
import { act } from 'react';
import WarehousePage from '../WarehousePage';

describe('Warehouse Page', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    // tests if the Warehouses title appears on the Warehouses page
    test('should render the warehouses title', () => {
         act( () => {
            render(<WarehousePage />);
        });
    
        expect(screen.getByText('Warehouses')).toBeInTheDocument();
    });

    // tests if the Warehouses page has the sorting label displayed
    test('should render the sort warehouses label', () => {
        act( () => {
           render(<WarehousePage />);
       });
   
       expect(screen.getByText('Sort warehouses by:')).toBeInTheDocument();
   });
})