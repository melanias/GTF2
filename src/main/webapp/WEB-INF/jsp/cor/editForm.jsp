                <div class="content-header">
                    <h1>
                        ${title}
                    </h1>
                </div>

                <!-- content -->
                <div class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <form name="form" class="form-horizontal" novalidate>
                                <div class="box box-default">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">
                                            ${subTitle}
                                        </h3>

                                        <div class="box-tools pull-right">
                                            <a ui-sref="cor" class="btn btn-box-tool" role="button" title="Voltar"><i class="fa fa-lg fa-chevron-left"></i></a>
                                        </div>
                                    </div>
                                    <!-- ./box-header -->

                                    <div class="box-body">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <input type="hidden" ng-model="cor.id">

                                                <label for="nome">Nome da cor</label>
                                                <input type="text" ng-model="cor.nome" class="form-control input-sm" id="nome" name="nome" maxlength="50" required>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- ./box-body -->

                                    <div class="box-footer">
                                        <a ui-sref="cor" class="btn btn-sm btn-default" role="button">Cancelar</a>
                                        <button class="btn btn-sm btn-primary" ng-click="updateColor()" type="button" ng-disabled="form.nome.$invalid">Salvar</button>
                                    </div>
                                </div>
                                <!-- ./box -->
                            </form>
                            <div class="alert alert-danger alert-dismissable" ng-show="errors.length > 0">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <p ng-repeat="error in errors"><strong>{{error.message}}</strong></p>
                            </div>
                        </div>
                        <!-- ./col-xs-12 -->
                    </div>
                    <!-- ./row -->
                </div>
                <!-- ./content -->