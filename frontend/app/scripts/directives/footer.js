/**
 * Created by redbee on 15/06/16.
 */
angular.module('dutymap')
  .directive('footerDutymap', function () {
    return {

      templateUrl:'views/directives/footer.html',
      restrict: 'E',
      replace:true
    };
  });
