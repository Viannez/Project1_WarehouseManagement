import React from 'react';
import {render, screen } from '@testing-library/react';
import { act } from 'react';
import WarehouseDetails from '../WarehouseDetails';

describe('Warehouse Details', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    // tests if the Warehouse title appears on the Warehouse Detail page
    test('should render the warehouse title', () => {
         act( () => {
            render(<WarehouseDetails />);
        });
    
        expect(screen.getByText('Warehouse')).toBeInTheDocument();
    });

    // tests if the Warehouse Detail page has the details of the warehouse displayed
    test('should render the warehouse details', () => {
        act( () => {
           render(<WarehouseDetails />);
       });
   
       expect(screen.getByText('Name:')).toBeInTheDocument();
       expect(screen.getByText('Location:')).toBeInTheDocument();
       expect(screen.getByText('Capacity filled:')).toBeInTheDocument();
       expect(screen.getByText('This warehouse has the following products in stock:')).toBeInTheDocument();
   });
})