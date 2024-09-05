import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TimeKeeping from './time-keeping';
import TimeKeepingDetail from './time-keeping-detail';
import TimeKeepingUpdate from './time-keeping-update';
import TimeKeepingDeleteDialog from './time-keeping-delete-dialog';

const TimeKeepingRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TimeKeeping />} />
    <Route path="new" element={<TimeKeepingUpdate />} />
    <Route path=":id">
      <Route index element={<TimeKeepingDetail />} />
      <Route path="edit" element={<TimeKeepingUpdate />} />
      <Route path="delete" element={<TimeKeepingDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TimeKeepingRoutes;
