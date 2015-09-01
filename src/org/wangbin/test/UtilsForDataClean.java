package org.wangbin.test;

import java.io.File;

/**
 * 清洗数据，数据量小，700w数据 可以用这个比较简单的方法
 * 
 * @author wb
 * @date 2015-8-27 上午9:53:56
 */
public class UtilsForDataClean {
    public String setCounterService(long uid) {
        // TODO Auto-generated method stub

        try {
            String dataDir = "/data1/favorites/result";
             String dataDir = "C:"+File.separator
             + "Users"+File.separator + "wb"+File.separator + "Desktop"+File.separator +
             "favorite" +File.separator + "result";
            // final String todataDir = "C:"+File.separator
            // + "Users"+File.separator + "wb"+File.separator + "Desktop"+File.separator +
            // "favorite" +File.separator + "endResult"+File.separator;
            final String todataDir = "/data1/favorites/endResult/";
            File folder = new File(dataDir);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                long startTime = System.currentTimeMillis();
                if (file.isFile()) {
                    ApiLogger.info("setCounterService favorites load data :" + file.getName());
                    final File finalFile = file;
                    READ_FILE_POOL.execute(new Runnable() {
                        @Override
                        public void run() {
                            // 这个地方可以改为try-with-resources的写法，代码更精简。囧maven没过so用的try-finally
                            try {
                                final FileWriter writer = new FileWriter(todataDir + finalFile.getName(), true);
                                try {
                                    Files.readLines(finalFile, Charset.defaultCharset(), new LineProcessor<String>() {
                                        @Override
                                        public boolean processLine(String line) {
                                            // 读一行，打一行
                                            ApiLogger.info("setCounterService get@@#@@ : " + line);
                                            // 出错了，打印日志，继续下一行
                                            try {
                                                if (StringUtils.isNotBlank(line)) {
                                                    String[] strs = StringUtils.split(line, " ");
                                                    String uid = StringUtils.substringBetween(strs[0], "\"", ".favl");
                                                    if (strs.length > 1) {
                                                        String score = strs[1];
                                                        if (StringUtils.isNotBlank(score) && StringUtils.isNumericSpace(score)) {
                                                            score = score.replaceAll(" ", "");
                                                            int lon = Integer.valueOf(score);
                                                            long uidL = Long.valueOf(uid);
                                                            if (lon > 0l) {
                                                                // 先获取，如果不为0，才写入。。如果有数据比较大小，以大的为准
                                                                Integer targetUidLon =
                                                                        counterService
                                                                                .getCounter(uidL, CounterType.favoriteLikeToMeCounter);
                                                                boolean resultSet = false;
                                                                if (targetUidLon != null && targetUidLon == 0) {
                                                                    // 先setCounter，然后写入校验日志
                                                                    resultSet =
                                                                            counterService.setCounter(uidL, lon,
                                                                                    CounterType.favoriteLikeToMeCounter);
                                                                } else {
                                                                    Integer resultFromRedis =
                                                                            (int) favoriteUserLikeRedisDao.getUserLikeListCount(uidL);
                                                                    if (resultFromRedis != targetUidLon) {
                                                                        resultSet =
                                                                                counterService.setCounter(uidL, lon,
                                                                                        CounterType.favoriteLikeToMeCounter);
                                                                    }
                                                                }
                                                                if (resultSet) {
                                                                    writer.write("\"" + uid + ".favl" + "\" " + lon + "\n");
                                                                } else {
                                                                    // counterService出错了
                                                                    writer.write("\"" + uid + ".favl" + "\" " + lon
                                                                            + "   false~~~or~~unset Counter" + "\n");
                                                                }
                                                            }
                                                            ApiLogger.error("##@## FavoriteServiceImpl.setCounterService  :"
                                                                    + finalFile.getName() + " ~~ " + line);
                                                        } else {
                                                            ApiLogger.error("##@## FavoriteServiceImpl.setCounterService  :"
                                                                    + finalFile.getName() + " ~~ " + line);
                                                        }
                                                    } else {
                                                        ApiLogger.error("##@## FavoriteServiceImpl.setCounterService  :"
                                                                + finalFile.getName() + " ~~ " + line);
                                                    }
                                                }
                                            } catch (Exception e) {
                                                ApiLogger.error("##@## FavoriteServiceImpl.setCounterService  :" + finalFile.getName()
                                                        + " ~~ " + line);
                                                return true;
                                            } finally {
                                                // 不管是异常还是正确，都打印出来
                                                ApiLogger.info("setCounterService finished@@#@@ : " + line);
                                            }
                                            return true;
                                        }

                                        @Override
                                        public String getResult() {
                                            return null;
                                        }
                                    });
                                } finally {
                                    if (writer != null) {
                                        writer.close();
                                    }
                                }
                            } catch (Exception e) {
                                ApiLogger.error("##@## FavoriteServiceImpl.setCounterService  :" + finalFile.getName() + " ~~ " + e);
                            }
                        }
                    });

                }
                ApiLogger.info("setCounterService favorites load data  File total time: " + (System.currentTimeMillis() - startTime)
                        + " end~~~");
            }
        } catch (Exception e) {
            // TODO: handle exception
            ApiLogger.error("ExceptionAll@@#@@ FavoriteServiceImpl.setCounterService  :" + e);
        }
        return new JsonBuilder().append("清洗数据 : ", "完成").flip().toString();

    }

    public String checkoutCounter(long uid) {
        // TODO Auto-generated method stub

        try {
            String dataDir = "/data1/favorites/endResult";
            final String todataDir = "/data1/favorites/checkout/";
            File folder = new File(dataDir);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                long startTime = System.currentTimeMillis();
                if (file.isFile()) {
                    ApiLogger.info("checkoutCounter favorites load data :" + file.getName());
                    final File finalFile = file;
                    READ_FILE_POOL.execute(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                final FileWriter writer = new FileWriter(todataDir + finalFile.getName(), true);
                                try {
                                    Files.readLines(finalFile, Charset.defaultCharset(), new LineProcessor<String>() {
                                        @Override
                                        public boolean processLine(String line) throws IOException {
                                            // 读一行，打一行
                                            ApiLogger.info("get@@#@@check: " + line);
                                            // 出错了，打印日志，继续下一行
                                            try {
                                                if (StringUtils.isNotBlank(line)) {
                                                    String[] strs = StringUtils.split(line, " ");
                                                    String uid = StringUtils.substringBetween(strs[0], "\"", ".favl");
                                                    if (strs.length > 1) {
                                                        String score = strs[1];
                                                        if (StringUtils.isNotBlank(score) && StringUtils.isNumericSpace(score)) {
                                                            score = score.replaceAll(" ", "");
                                                            int lon = Integer.valueOf(score);
                                                            long uidL = Long.valueOf(uid);
                                                            // 校验数据这个地方先暂时按照每行查取
                                                            Integer resultSetCounter =
                                                                    counterService.getCounter(uidL, CounterType.favoriteLikeToMeCounter);
                                                            Integer resultFromRedis =
                                                                    (int) favoriteUserLikeRedisDao.getUserLikeListCount(uidL);
                                                            if (resultSetCounter != resultFromRedis) {
                                                                writer.write("\"" + uid + ".favl" + "\" " + lon
                                                                        + "   false~~~counterService:" + resultSetCounter + " ~~redis: +"
                                                                        + resultFromRedis + "\n");
                                                            }
                                                        } else {
                                                            ApiLogger.error("check##@## FavoriteServiceImpl.checkoutCounter  check:"
                                                                    + finalFile.getName() + "~~" + line);
                                                        }
                                                    } else {
                                                        ApiLogger.error("check##@## FavoriteServiceImpl.checkoutCounter  check:"
                                                                + finalFile.getName() + "~~" + line);
                                                    }
                                                }
                                            } catch (Exception e) {
                                                // TODO: handle exception
                                                ApiLogger.error("check##@## FavoriteServiceImpl.checkoutCounter  check:"
                                                        + finalFile.getName() + "~~" + line);
                                                return true;
                                            } finally {
                                                // 不管是异常还是正确，都打印出来
                                                ApiLogger.info("checkoutCounter finished@@#@@ : " + line);
                                            }
                                            return true;
                                        }

                                        @Override
                                        public String getResult() {
                                            return null;
                                        }
                                    });
                                } finally {
                                    if (writer != null) {
                                        writer.close();
                                    }
                                }
                            } catch (Exception e) {
                                ApiLogger.error("check##@## FavoriteServiceImpl.checkoutCounter  check:" + finalFile.getName());
                            }
                        }
                    });

                }
                ApiLogger.info("checkoutCounter File total time: " + (System.currentTimeMillis() - startTime) + " end~~~");
            }
        } catch (Exception e) {
            // TODO: handle exception
            ApiLogger.error("ExceptionAll FavoriteServiceImpl.checkoutCounter  check:" + e);
        }
        return new JsonBuilder().append("校验数据: ", "完成").flip().toString();

    }

    public String fixCheckoutCounter(long uid) {
        // TODO Auto-generated method stub

        try {
            String dataDir = "/data1/favorites/checkout";
            final String todataDir = "/data1/favorites/fixcheckout/";
            File folder = new File(dataDir);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                long startTime = System.currentTimeMillis();
                if (file.isFile()) {
                    ApiLogger.info("fixCheckoutCounter favorites load data :" + file.getName());
                    final File finalFile = file;
                    READ_FILE_POOL.execute(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                final FileWriter writer = new FileWriter(todataDir + finalFile.getName(), true);
                                try {
                                    Files.readLines(finalFile, Charset.defaultCharset(), new LineProcessor<String>() {
                                        @Override
                                        public boolean processLine(String line) throws IOException {
                                            // 读一行，打一行
                                            ApiLogger.info("get@@#@@fix: " + line);
                                            // 出错了，打印日志，继续下一行
                                            try {
                                                if (StringUtils.isNotBlank(line)) {
                                                    String[] strs = StringUtils.split(line, " ");
                                                    String uid = StringUtils.substringBetween(strs[0], "\"", ".favl");
                                                    if (strs.length > 1) {
                                                        String score = strs[1];
                                                        if (StringUtils.isNotBlank(score) && StringUtils.isNumericSpace(score)) {
                                                            score = score.replaceAll(" ", "");
                                                            int lon = Integer.valueOf(score);
                                                            long uidL = Long.valueOf(uid);
                                                            // 校验数据这个地方先暂时按照每行查取
                                                            Integer resultSetCounter =
                                                                    counterService.getCounter(uidL, CounterType.favoriteLikeToMeCounter);
                                                            Integer resultFromRedis =
                                                                    (int) favoriteUserLikeRedisDao.getUserLikeListCount(uidL);
                                                            if (resultSetCounter != resultFromRedis) {
                                                                boolean setResult =
                                                                        counterService.setCounter(uidL, resultFromRedis,
                                                                                CounterType.favoriteLikeToMeCounter);
                                                                if (setResult) {
                                                                    writer.write("\"" + uid + ".favl" + "\" " + lon
                                                                            + "   setCounter true\n");
                                                                } else {
                                                                    writer.write("\"" + uid + ".favl" + "\" " + lon
                                                                            + "   setCounter false\n");
                                                                }
                                                            }
                                                        } else {
                                                            ApiLogger.error("fix##@## FavoriteServiceImpl.fixCheckoutCounter  check:"
                                                                    + finalFile.getName() + "~~ " + line);
                                                        }
                                                    } else {
                                                        ApiLogger.error("fix##@## FavoriteServiceImpl.fixCheckoutCounter  check:"
                                                                + finalFile.getName() + "~~ " + line);
                                                    }
                                                }
                                            } catch (Exception e) {
                                                // TODO: handle exception
                                                ApiLogger.error("fix##@## FavoriteServiceImpl.fixCheckoutCounter  check:"
                                                        + finalFile.getName() + "~~ " + line);
                                                return true;
                                            } finally {
                                                // 不管是异常还是正确，都打印出来
                                                ApiLogger.info("fixCheckoutCounter finished  : " + line);
                                            }
                                            return true;
                                        }

                                        @Override
                                        public String getResult() {
                                            return null;
                                        }
                                    });
                                } finally {
                                    if (writer != null) {
                                        writer.close();
                                    }
                                }
                            } catch (Exception e) {
                                ApiLogger.error("fix@@#@@ FavoriteServiceImpl.fixCheckoutCounter  :" + e);
                            }
                        }
                    });

                }
                ApiLogger.info("fixCheckoutCounter File total time: " + (System.currentTimeMillis() - startTime) + " end~~~");
            }
        } catch (Exception e) {
            // TODO: handle exception
            ApiLogger.error("ExceptionAll FavoriteServiceImpl.fixCheckoutCounter  :" + e);
        }
        return new JsonBuilder().append("修复校验数据: ", "完成").flip().toString();

    }

    public String justGetCounter(long uid) {
        // TODO Auto-generated method stub
        try {
            Integer result = counterService.getCounter(uid, CounterType.favoriteLikeToMeCounter);
            Integer resultFromRedis = (int) favoriteUserLikeRedisDao.getUserLikeListCount(uid);
            JsonBuilder json = new JsonBuilder();
            return json.append(uid + ":", "counter:" + result + "   redis:" + resultFromRedis).flip().toString();
        } catch (Exception e) {
            // TODO: handle exception
            ApiLogger.error("justGet@@#@@ FavoriteServiceImpl.justGetCounter  :" + e);
        }
        return null;
    }
}
